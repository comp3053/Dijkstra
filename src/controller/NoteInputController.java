package controller;

import model.Note;
import view.NoteListView;

import javax.swing.*;

public class NoteInputController {
    private Note m;

    public NoteInputController(Note m) {
        this.m = m;
    }

    public boolean saveNote(Note m, String content) {
        m.setContent(content);
        if (m.getID() == 0) {
            if (m.insert()) {
                NoteListController nlc = new NoteListController();
                NoteListView nlv = new NoteListView(nlc);
                nlv.setVisible(true);
                return true;
            } else {
                return false;
            }
        } else {
            if (m.update()) {
                NoteListController nlc = new NoteListController();
                NoteListView nlv = new NoteListView(nlc);
                nlv.setVisible(true);
                return true;
            } else {
                return false;
            }
        }
    }

    public int backToNoteList() {
        // Return a status, check need close the current window or not
        int isSave = JOptionPane.showConfirmDialog(null, "Are you sure to leave without saving your note?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (isSave == 0) {
            NoteListController nlc = new NoteListController();
            NoteListView nlv = new NoteListView(nlc);
            nlv.setVisible(true);
            return 1;
        } else if (isSave == 1 || isSave == 2) {
            // Do Nothing
            return 0;
        }
        return 0; // Default: return 0 and do nothing
    }
}
