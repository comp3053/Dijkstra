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
    public BrewingHistoryListController(){
    }

    public ArrayList<BrewingRecord> readBrewingHistoryList(){
        try {
            ArrayList<BrewingRecord> brewingRecords = new ArrayList<>();
            brewingRecords = BrewingRecord.getAllBrewingRecord();
            return brewingRecords;
        } catch (FetchDataException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void goBack(){
        NoteListController nlc = new NoteListController();
        NoteListView nlv = null;
        nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }

    public void takeNote(BrewingRecord m){
        System.out.println("Taking notes...");
        Date currentTime = new Date();
        Note newNote = new Note(0,m.getID(),currentTime,"");
        NoteInputController nic = new NoteInputController(newNote);
        NoteInputView niv = new NoteInputView(nic, newNote);
        niv.setVisible(true);
    }
}
