package test;

import model.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteTest {
    //initial part
    private static Note note;
    java.util.Date date = new java.util.Date();

    @Before
    public void setUp(){// set up the note
        note = new Note(1,date,"java is so funny.");
    }

    @Test
    public void getID() {// test whether it can get the ID
        assertEquals(1,note.getID());
    }

    @Test
    public void setID() {// test whether it can set the ID
        note.setID(2);
        assertEquals(2,note.getID());
    }

    @Test
    public void getCreateDate() {// test whether it can get create date
        assertEquals(date,note.getCreateDate());
    }

    @Test
    public void setCreateDate() {// test whether it can set create date
        java.util.Date modifyDate = new java.util.Date();
        assertEquals(modifyDate,note.getCreateDate());
    }

    @Test
    public void getContent() {// test whether it can get content
        assertEquals("java is so funny.",note.getContent());
    }

    @Test
    public void setContent() {// test whether it can set content
        note.setContent("GPA4.0!");
        assertEquals("GPA4.0!",note.getContent());
    }
}