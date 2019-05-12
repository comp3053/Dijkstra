package controller;

import model.BrewingRecord;
import model.Note;
import utils.FetchDataException;
import utils.ObjectNotFoundException;
import view.NoteInputView;
import view.NoteListView;

import java.util.ArrayList;
import java.util.Date;

public class BrewingHistoryListController {

    public BrewingHistoryListController() {
        // Nothing to do
    }

    public ArrayList<BrewingRecord> readBrewingHistoryList() {
        ArrayList<BrewingRecord> brewingRecords = new ArrayList<>();
        brewingRecords = BrewingRecord.getAll();
        return brewingRecords;
    }

    public void goBack() {
        NoteListController nlc = new NoteListController();
        NoteListView nlv = null;
        nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }

    public void takeNote(BrewingRecord m) {
        System.out.println("Taking notes...");
        Date currentTime = new Date();
        Note newNote = new Note(0, m.getID(), currentTime, "");
        NoteInputController nic = new NoteInputController(newNote);
        NoteInputView niv = new NoteInputView(nic, newNote);
        niv.setVisible(true);
    }
}
