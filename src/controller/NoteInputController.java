package controller;

import model.Note;

import javax.swing.*;
import java.util.Date;

public class NoteInputController {
    public NoteInputController(){

    }

    public boolean saveNote(int brewID, String content){
        Note note = new Note(brewID, new Date(), content);
        NoteController nc = new NoteController();
        return nc.insert(note);
    }

    public void backToNoteList(){
        int isSave = JOptionPane.showConfirmDialog(null,"Are you sure to leave without saving your note?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
    }
}
