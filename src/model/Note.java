package model;

import controller.ModelListener;
import utils.DatabaseHelper;
import utils.FetchDataException;
import utils.SQLiteConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class Note implements IDatabaseOperation<Note> {
    private int id;
    private int brewID;
    private Date createDate;
    private String content;
    private ModelListener listener;

    /* Initialize the Note model */
    public Note(int brewID, Date createDate, String content) {
        setBrewID(brewID);
        setCreateDate(createDate);
        setContent(content);
    }

    public Note(int id, int brewID, Date createDate, String content) {
        setID(id);
        setBrewID(brewID);
        setCreateDate(createDate);
        setContent(content);
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    private void setBrewID(int brewID) {
        this.brewID = brewID;
    }

    public int getBrewID() {
        return brewID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    private void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /* The methods that will insert the information of this instance into the database */
    public boolean insert() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        try {
            String query = String.format("INSERT INTO Note (Create_Date, Content, Brew_ID) VALUES (%d,'%s',%d);",
                    this.getCreateDate().getTime(), stringParser(this.getContent()), this.getBrewID());
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
            // Get the latest Note ID
            dbHelper.connectSQLite();
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Note ORDER BY Note_ID DESC LIMIT 1");
            this.setID(rs.getInt(1));
            dbHelper.closeConnection();
            System.out.println("The brew ID is " + this.getBrewID());
            // Set Note ID for corresponding Brew Record
            dbHelper.connectSQLite();
            query = String.format("UPDATE Brew SET Note_ID = %d WHERE Brew_ID = %d;",
                    this.getID(), this.getBrewID());
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /* The methods that will update the information of this instance into the database */
    public boolean update() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("UPDATE Note SET Content='%s' WHERE Brew_ID=%d",
                stringParser(this.getContent()), this.getBrewID());
        try {
            dbHelper.execSqlUpdate(query);
            dbHelper.closeConnection();
            return true;
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* The methods that get all the note existed in the database, it needs for the view to list all note records */
    public static ArrayList<Note> getAll() {
        ArrayList<Note> notes = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = "SELECT * FROM Note";
        int id, brewID;
        Date createDate;
        String content;
        String CreateDateString;
        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            while (rs.next()) {
                id = rs.getInt(1);
                CreateDateString = rs.getString(2);
                //System.out.println(CreateDateString);
                long lt = new Long(CreateDateString);
                createDate = new Date(lt);
                //System.out.println(createDate);
                content = rs.getString(3);
                brewID = rs.getInt(4);
                notes.add(new Note(id, brewID, createDate, content));
            }
            dbHelper.closeConnection();
            return notes;
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* This methods will delete the inputting methods of the database */
    public boolean delete() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        try {
            String query = String.format("DELETE FROM Note WHERE Note_ID = %d;",
                    this.getID());
            dbHelper.execSqlNoReturn(query);
            query = String.format("UPDATE Brew SET Note_ID = %d WHERE Brew_ID = %d;",
                    0, this.getBrewID());
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
            notifyListener();
            return true;
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void addListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyListener() {
        this.listener.update();
    }
}

