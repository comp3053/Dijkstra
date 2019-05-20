package controller;

import model.Note;
import view.NoteListView;

import javax.swing.*;

public class NoteInputController {
    private Note m;

    public NoteInputController(Note m) {
        this.m = m;
    }

    /**
     * Save note to database.
     * @param m Note you want to save to database.
     * @param content Content of current note.
     * @return Whether save note operation done successfully.
     */
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

    /**
     * Go back to note list view.
     * @return A status to check if we need to close the current window. 1 -> Go back; 0 -> Do nothing.
     */
    public int backToNoteList() {
        int isSave = JOptionPane.showConfirmDialog(null, "Are you sure to leave without saving your note?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (isSave == 0) {
            NoteListController nlc = new NoteListController();
            NoteListView nlv = new NoteListView(nlc);
            nlv.setVisible(true);
            return 1;
        } else if (isSave == 1) {
            // Do Nothing
            return 0;
        }
        return 0; // Default: return 0 and do nothing
    }
}
