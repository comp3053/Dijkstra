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
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch Equipment information.");
        }
        return new Equipment(name, volume);

    }

    public static void main(String[] args) {
        EquipmentController ec = new EquipmentController();
        try {
            Equipment equipment = ec.getEquipment();
            System.out.println(equipment.getName());
            System.out.println(equipment.getVolume());
        } catch (FetchDataException | InvalidEquipmentVolumeException | EmptyEquipmentNameException e) {
            e.printStackTrace();
        }
    }
}
