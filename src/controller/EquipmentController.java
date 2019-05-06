package controller;

import model.Equipment;

import java.util.ArrayList;

public class EquipmentController implements DatabaseController<Equipment> {
    public EquipmentController() {
        // Nothing to do
    }

    public ArrayList<Equipment> getAll() {
//        DatabaseHelper dbHelper = new DatabaseHelper();
        ArrayList<Equipment> equipments = new ArrayList<>();
//        String name;
//        int volume;
//
//        try {
//            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Equipment");
//            while (rs.next()) {
//                name = rs.getString(2);
//                volume = rs.getInt(3);
//                equipments.add(new Equipment(name, volume));
//            }
//            dbHelper.closeConnection();
//        } catch (SQLException | SQLiteConnectionException e) {
//            e.printStackTrace();
//            throw new FetchDataException("Could not fetch Equipment information.");
//        }
        return equipments;
    }

    public boolean insert(Equipment equipment) { // Do not use this directly
//        DatabaseHelper dbHelper = new DatabaseHelper();
//        String query = String.format("INSERT INTO Equipment VALUES (1,'%s',%d)",
//                equipment.getName(), equipment.getVolume());
//        try {
//            dbHelper.execSqlNoReturn(query);
//            dbHelper.closeConnection();
//        } catch (SQLiteConnectionException e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }

    public boolean update(Equipment equipment) {
//        DatabaseHelper dbHelper = new DatabaseHelper();
//        EquipmentController ec = new EquipmentController();
//        String query = String.format("UPDATE Equipment SET Name='%s',Volume=%d WHERE Equipment_ID=1",
//                equipment.getName(), equipment.getVolume());
//        try {
//            ec.getAll();
//            dbHelper.closeConnection();
//        } catch (InvalidInputException | EmptyNameException e) {
//            e.printStackTrace();
//            return false;
//        } catch (FetchDataException e) {
//            insert(equipment);
//        }
//        dbHelper.connectSQLite();
//        try {
//            dbHelper.execSqlNoReturn(query);
//            dbHelper.closeConnection();
//        } catch (SQLiteConnectionException e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }
//
//    public static void main(String[] args) {
//        EquipmentController ec = new EquipmentController();
//        try {
//            Equipment equipment = new Equipment("MyEquipment", 200);
//            ec.update(equipment);
//            System.out.println(ec.getAll().get(0).getName());
//        } catch (FetchDataException | InvalidInputException | EmptyNameException e) {
//            e.printStackTrace();
//        }
//    }
}

//TODO: REMOVE LATER