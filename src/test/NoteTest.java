package test;

import model.Note;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class NoteTest {

    private static Note note;
    java.util.Date date = new java.util.Date();

    @Before
    public void setUp(){
        note = new Note(1,date,"java is so funny.");
    }

    @Test
    public void getID() {
        assertEquals(1,note.getID());
    }

    @Test
    public void setID() {
        note.setID(2);
        assertEquals(2,note.getID());
    }

    @Test
    public void getCreateDate() {
        assertEquals(date,note.getCreateDate());
    }

    @Test
    public void setCreateDate() {
        java.util.Date modifyDate = new java.util.Date();
        assertEquals(modifyDate,note.getCreateDate());
    }

    @Test
    public void getContent() {
        assertEquals("java is so funny.",note.getContent());
    }

    @Test
    public void setContent() {
        note.setContent("GPA4.0!");
        assertEquals("GPA4.0!",note.getContent());
    }
}