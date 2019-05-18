package model;

import controller.ModelListener;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipment implements IDatabaseOperation {
    private String name;
    private int volume;
    private ModelListener listener;
    
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

    public void setModelListener(ModelListener listener) {
        this.listener = listener;
    }

    /**
     * Get single equipment by id
     * @param id Default value is 1, to get the only equipment in database
     * @return Equipment object of only equipment in database
     * @throws FetchDataException Throws when could not fetch data from database
     * @throws InvalidInputException Throws when equipment's amount is invalid
     * @throws EmptyNameException Throws when equipment's name is empty
     */
    public static Equipment getEquipment(int id) throws FetchDataException, InvalidInputException, EmptyNameException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        Equipment dbEquipment;
        String name;
        int volume;

        // Get the information of only equipment in database.
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

    /**
     * Insert the current equipment model to database.
     * Attention: Do not use it directly because database could only contain 1 equipment!
     * @return Whether the insert operation is successful
     */
    public boolean insert() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("INSERT INTO Equipment (Equipment_ID, Name, Volume) VALUES (1,'%s',%d)",
                stringParser(this.getName()), this.getVolume());

        // Insert current equipment into database if equipment not exist.
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Update the information of equipment by current object.
     * @return Whether the update operation is successful
     */
    @Override
    public boolean update() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("UPDATE Equipment SET Name='%s',Volume=%d WHERE Equipment_ID=1",
                stringParser(this.getName()), this.getVolume());

        // Update equipment's information
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
        // Equipment cannot be deleted
        return false;
    }

    @Override
    public void addListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyListener() {
        listener.update();
    }

}