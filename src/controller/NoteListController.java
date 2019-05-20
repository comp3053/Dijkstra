package controller;

import model.BrewingRecord;
import model.Note;
import utils.FetchDataException;
import view.BrewingHistoryListView;
import view.HomeView;
import view.NoteInputView;

import javax.swing.*;
import java.util.ArrayList;

public class NoteListController {
    public NoteListController() {
    }

    /**
     * Go back to homepage.
     */
    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    /**
     * Get all the notes from database.
     * @return ArrayList of all notes in database.
     */
    public ArrayList<Note> readNoteList() {
        ArrayList<Note> notes;
        notes = Note.getAll();
        return notes;
    }

    /**
     * Go to the note edit view.
     * @param m Note you want to modify.
     */
    public void modify(Note m) {
        System.out.println("modify");
        NoteInputController nc = new NoteInputController(m);
        NoteInputView hv = new NoteInputView(nc, m);
        hv.setVisible(true);
    }

    /**
     * Delete note you chose.
     * @param m The information of note you want to delete.
     */
    public void delete(Note m) {
        System.out.println("delete");
        int isSave = JOptionPane.showConfirmDialog(null, "Are you sure to delete your note?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (isSave == 0) {
            if (m.delete())
                JOptionPane.showMessageDialog(null, "Your note has been deleted", "Success", JOptionPane.PLAIN_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Error!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Your note has not been deleted ", "Success", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * List the brewing records you can add notes and go to note editing view.
     */
    public void addNote() {
        BrewingHistoryListController bhlc = new BrewingHistoryListController();
        BrewingHistoryListView bhlv = new BrewingHistoryListView(bhlc, BrewingRecord.getAll());
        bhlv.setVisible(true);
    }
}
