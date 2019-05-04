package controller;

import model.EmptyEquipmentNameException;
import model.Equipment;
import model.InvalidEquipmentVolumeException;
import model.Recipe;
import utils.DatabaseHelper;
import utils.SQLiteConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentController implements DatabaseController<Equipment> {
    public EquipmentController() {
        // Nothing to do
    }

    public ArrayList<Equipment> getAll() throws FetchDataException, InvalidEquipmentVolumeException, EmptyEquipmentNameException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        ArrayList<Equipment> equipments = new ArrayList<>();
        String name;
        int volume;

        try {
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Equipment");
            while (rs.next()) {
                name = rs.getString(2);
                volume = rs.getInt(3);
                equipments.add(new Equipment(name, volume));
            }
            dbHelper.closeConnection();
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch Equipment information.");
        }
        return equipments;
    }

    public boolean insert(Equipment equipment) { // Do not use this directly
        DatabaseHelper dbHelper = new DatabaseHelper();
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

    public boolean update(Equipment equipment) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        EquipmentController ec = new EquipmentController();
        String query = String.format("UPDATE Equipment SET Name='%s',Volume=%d WHERE Equipment_ID=1",
                equipment.getName(), equipment.getVolume());
        try {
            ec.getAll();
            dbHelper.closeConnection();
        } catch (InvalidEquipmentVolumeException | EmptyEquipmentNameException e) {
            e.printStackTrace();
            return false;
        } catch (FetchDataException e) {
            insert(equipment);
        }
        System.out.println("Fuck you!");
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

    public Equipment getLatestEquipment()throws FetchDataException, InvalidEquipmentVolumeException, EmptyEquipmentNameException{
        DatabaseHelper dbHelper = new DatabaseHelper();
        Equipment equipment;
        String query = "SELECT * FROM Equipment WHERE Equipment_ID=(SELECT MAX(Equipment_ID) FROM Equipment)";

        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            String name =rs.getString(2);
            int volume = rs.getInt(3);
            equipment = new Equipment(name,volume);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException |SQLException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not get Equipment");
        }
        return equipment;
    }
//
//    public static void main(String[] args) {
//        EquipmentController ec = new EquipmentController();
//        try {
//            Equipment equipment = new Equipment("MyEquipment", 200);
//            ec.update(equipment);
//            System.out.println(ec.getAll().get(0).getName());
//        } catch (FetchDataException | InvalidEquipmentVolumeException | EmptyEquipmentNameException e) {
//            e.printStackTrace();
//        }
//    }
}
