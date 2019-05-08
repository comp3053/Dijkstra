package model;

import controller.ModelListener;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipment implements IDatabaseOperation<Equipment> {
    private String name;
    private int volume;
    private ModelListener ml;


    public Equipment(String name, int volume) throws InvalidInputException, EmptyNameException {
        setName(name);
        setVolume(volume);
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) throws EmptyNameException {
        if (name.isEmpty()) {
            throw new EmptyNameException("Equipment name cannot be empty!");
        } else {
            this.name = name;
        }
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) throws InvalidInputException {
        if (volume > 0) {
            this.volume = volume;
        } else {
            throw new InvalidInputException("Volume should be greater than 0!");
        }
    }

    public void setModelListener(ModelListener ml){
        this.ml = ml;
    }

    // Get single equipment by id, static
    public static Equipment getEquipment(int id) throws FetchDataException, InvalidInputException, EmptyNameException {
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
        String query = String.format("INSERT INTO Equipment (Equipment_ID, Name, Volume) VALUES (1,'%s',%d)",
                stringParser(this.getName()), this.getVolume());
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
                stringParser(this.getName()), this.getVolume());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
            return true;
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() {
        return false;
        // Equipment cannot be deleted
    }

    @Override
    public void addListener(ModelListener listener) {
        this.ml = listener;
    }

    @Override
    public void notifyListener() {
        ml.update();
    }

}