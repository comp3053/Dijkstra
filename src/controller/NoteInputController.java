package controller;

import javax.swing.*;

public class NoteInputController {
    public NoteInputController(){

    }

    public void saveNote(){
        System.out.println("Save note");
    }

    public void backToNoteList(){
        int isSave = JOptionPane.showConfirmDialog(null,"Are you sure to leave without saving your note?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
    }
}
