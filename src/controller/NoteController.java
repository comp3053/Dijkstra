package controller;

import model.Note;
import utils.DatabaseHelper;
import utils.SQLiteConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class NoteController implements DatabaseController<Note> {
    public NoteController() {
        //Nothing to do
    }

    public ArrayList<Note> getAll() throws FetchDataException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        ArrayList<Note> notes = new ArrayList<Note>();
        int id, brewID;
        Date create_date;
        String content;

        try {
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Note");
            while (rs.next()) {
                id = rs.getInt(1);
                create_date = new Date(rs.getLong(2));
                content = rs.getString(3);
                brewID = rs.getInt(4);
                notes.add(new Note(id, brewID, create_date, content));
            }
            dbHelper.closeConnection();
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch Note information");
        }
        return notes;
    }

    public boolean insert(Note note) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        String query = String.format("INSERT INTO Note (Create_Date, Content, Brew_ID) VALUES (%d,'%s',%d)",
                note.getCreateDate().getTime(), note.getContent(), note.getBrewID());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Note note) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        String query = String.format("UPDATE Note SET Content='%s' WHERE Brew_ID=%d",
                note.getContent(), note.getBrewID());
        try {
            dbHelper.execSqlUpdate(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        NoteController nc = new NoteController();
        try {
            System.out.println(nc.getAll());
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }
}
