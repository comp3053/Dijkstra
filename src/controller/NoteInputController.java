package controller;

import model.Note;
import view.NoteListView;

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

    public int backToNoteList(){
        // return a status, check need close the current window or not
        int isSave = JOptionPane.showConfirmDialog(null,"Are you sure to leave without saving your note?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        if (isSave == 0){
            NoteListController nlc = new NoteListController();
            NoteListView nlv = new NoteListView(nlc);
            nlv.setVisible(true);
            return 1;
        }else if (isSave == 1 || isSave == 2){
            // Do Nothing
            return 0;
        }
        return 0; // default: return 0 and do nothing
    }
}
