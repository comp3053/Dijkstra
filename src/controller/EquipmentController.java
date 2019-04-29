package controller;

import model.EmptyEquipmentNameException;
import model.Equipment;
import model.InvalidEquipmentVolumeException;
import utils.DatabaseHelper;
import utils.SQLiteConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentController {
    public EquipmentController() {
        // Nothing to do
    }

    public Equipment getEquipment() throws FetchDataException, InvalidEquipmentVolumeException, EmptyEquipmentNameException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        String name;
        int volume;
        try {
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Equipment");
            name = rs.getString(2);
            volume = rs.getInt(3);
            dbHelper.closeConnection();
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch Equipment information.");
        }
        return new Equipment(name, volume);
    }

    private boolean insertEquipment(Equipment equipment) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        String query = String.format("INSERT INTO Equipment VALUES (1,'%s',%d)",
                equipment.getName(), equipment.getVolume());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setEquipment(Equipment equipment) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        EquipmentController ec = new EquipmentController();
        dbHelper.connectSQLite();
        String query = String.format("UPDATE Equipment SET name='%s',volume=%d WHERE Equipment_ID=1",
                equipment.getName(), equipment.getVolume());
        try {
            ec.getEquipment();
            dbHelper.closeConnection();
        } catch (InvalidEquipmentVolumeException | EmptyEquipmentNameException e) {
            e.printStackTrace();
            return false;
        } catch (FetchDataException e) {
            insertEquipment(equipment);
        }
        dbHelper.connectSQLite();
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        EquipmentController ec = new EquipmentController();
        try {
            Equipment equipment = new Equipment("MyEquipment", 2000);
            ec.setEquipment(equipment);
            System.out.println(ec.getEquipment().getName());
        } catch (FetchDataException | InvalidEquipmentVolumeException | EmptyEquipmentNameException e) {
            e.printStackTrace();
        }
    }
}
