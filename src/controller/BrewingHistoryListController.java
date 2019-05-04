package controller;

import view.BrewingHistoryListView;
import view.NoteInputView;
import view.NoteListView;

public class BrewingHistoryListController {
    public BrewingHistoryListController(){
    }

    public void readBrewingHistoryList(){

    }

    public void goBack(){
        NoteListController nlc = new NoteListController();
        NoteListView nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }

    public void takeNote(){
        System.out.println("it cannot pass a index in a loop directly, maybe I need to use a list to do it");
        NoteInputController nic = new NoteInputController();
        NoteInputView niv = new NoteInputView(nic, 3);
        niv.setVisible(true);
    }
}
