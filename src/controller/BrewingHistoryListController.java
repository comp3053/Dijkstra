package controller;

import model.BrewingRecord;
import model.Note;
import view.NoteInputView;
import view.NoteListView;

import java.util.Date;

public class BrewingHistoryListController {

    public BrewingHistoryListController() {
        // Nothing to do
    }

    /**
     * Go back to note list page.
     */
    public void goBack() {
        NoteListController nlc = new NoteListController();
        NoteListView nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }

    /**
     * Go to take note for a brewing record.
     * @param m Brewing record you want to take note.
     */
    public void takeNote(BrewingRecord m) {
        System.out.println("Taking notes...");
        Date currentTime = new Date();
        Note newNote = new Note(0, m.getID(), currentTime, "");
        NoteInputController nic = new NoteInputController(newNote);
        NoteInputView niv = new NoteInputView(nic, newNote);
        niv.setVisible(true);
    }
}
