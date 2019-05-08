package model;

import controller.ModelListener;
import utils.DatabaseHelper;
import utils.FetchDataException;
import utils.SQLiteConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Note {
    private int id;
    private int brewID;
    private Date createDate;
    private String content;
    private ModelListener ml;

    public void setModelListener(ModelListener ml){
        this.ml = ml;
    }

    public Note(){
    }

    public Note(int brewID, String content){
        setBrewID(brewID);
        setContent(content);
    }

    public Note(int brewID, Date createDate,String content){
        setBrewID(brewID);
        setCreateDate(createDate);
        setContent(content);
    }

    public Note(int id, int brewID, Date createDate,String content){
        setID(id);
        setBrewID(brewID);
        setCreateDate(createDate);
        setContent(content);
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
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

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public boolean insert() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("INSERT INTO Note (Create_Date, Content, Brew_ID) VALUES (%d,'%s',%d)",
                this.getCreateDate().getTime(), this.getContent(), this.getBrewID());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("UPDATE Note SET Content='%s' WHERE Brew_ID=%d",
                this.getContent(), this.getBrewID());
        try {
            dbHelper.execSqlUpdate(query);
            dbHelper.closeConnection();
            return true;
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Note> getAllNotes() throws FetchDataException {
        ArrayList<Note> notes = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = "SELECT * FROM Note";
        int id,brewID;
        Date createDate;
        String content;
        String CreateDateString;
        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            while(rs.next()) {
                id = rs.getInt(1);
                CreateDateString = rs.getString(2);
                //System.out.println(CreateDateString);
                long lt = new Long(CreateDateString);
                createDate = new Date(lt);
                //System.out.println(createDate);
                content = rs.getString(3);
                brewID = rs.getInt(4);
                notes.add(new Note(id,brewID,createDate,content));
            }
            dbHelper.closeConnection();
            return notes;
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("DELETE FROM Note WHERE Note_ID = %d",
                this.getID());
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

