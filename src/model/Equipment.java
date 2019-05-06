package model;

import controller.ModelListener;
import utils.DatabaseHelper;
import utils.SQLiteConnectionException;
import utils.FetchDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;

public class Equipment {
    private String name;
    private int volume;
    private ModelListener ml;


    public Equipment(String name, int volume) throws InvalidEquipmentVolumeException, EmptyEquipmentNameException {
        setName(name);
        setVolume(volume);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws EmptyEquipmentNameException {
        if (name.isEmpty()) {
            throw new EmptyEquipmentNameException("Equipment name cannot be empty!");
        } else {
            this.name = name;
        }
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) throws InvalidEquipmentVolumeException {
        if (volume > 0) {
            this.volume = volume;
        } else {
            throw new InvalidEquipmentVolumeException("Volume should be greater than 0!");
        }
    }

    public void setModelListener(ModelListener ml){
        this.ml = ml;
    }

    // Get single equipment by id, static
    public static Equipment getEquipment(int id) throws FetchDataException, InvalidEquipmentVolumeException, EmptyEquipmentNameException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        Equipment dbEquipment;
        String name;
        int volume;
        try {
            ResultSet rs = dbHelper.execSqlWithReturn(String.format("SELECT * FROM Equipment WHERE Equipment_ID = %d", id));
            name = rs.getString(2);
            volume = rs.getInt(3);
            dbEquipment = new Equipment(name, volume);
            dbHelper.closeConnection();
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch Equipment information.");
        }
        return dbEquipment;
    }

    // insert the current equipment model to the db
    public boolean insert() { // Do not use this directly
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("INSERT INTO Equipment VALUES (1,'%s',%d)",
                this.getName(), this.getVolume());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // update the equipment by current equipment object
    public boolean update() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("UPDATE Equipment SET Name='%s',Volume=%d WHERE Equipment_ID=1",
                this.getName(), this.getVolume());
        System.out.println("Query: " + query);
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
            return true;
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
    }

}