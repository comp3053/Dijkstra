package controller;

import model.BrewingRecord;
import model.Note;
import utils.FetchDataException;
import view.BrewingHistoryListView;
import view.HomeView;
import view.NoteInputView;
import view.NoteListView;

import javax.swing.*;
import java.util.ArrayList;

public class NoteListController {
    public NoteListController(){
    }

    public void goBack(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public ArrayList<Note> readNoteList(){
        ArrayList<Note> notes;
        try {
            notes = Note.getAllNotes();
            return notes;
        } catch (FetchDataException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void modify(Note m){
        System.out.println("modify");
        NoteInputController nc = new NoteInputController(m);
        NoteInputView hv = new NoteInputView(nc,m);
        hv.setVisible(true);
    }

    public void delete(Note m){
        System.out.println("delete");
        int isSave = JOptionPane.showConfirmDialog(null,"Are you sure to delete your note?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        if (isSave == 0){
            if(m.delete())
                JOptionPane.showMessageDialog(null,"Your note has been deleted","Success",JOptionPane.PLAIN_MESSAGE);
            else
                JOptionPane.showMessageDialog(null,"Error!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"Your note has not been deleted ","Success",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNote(){
        BrewingHistoryListController bhlc = new BrewingHistoryListController();
        BrewingHistoryListView bhlv = new BrewingHistoryListView(bhlc, BrewingRecord.getAll());
        bhlv.setVisible(true);
    }
}
