package controller;

import view.BrewingHistoryListView;
import view.NoteInputView;

public class BrewingHistoryListController {
    public BrewingHistoryListController(){
    }

    public void readBrewingHistoryList(){

    }

    public void goBack(){
        BrewingHistoryListController bhlc = new BrewingHistoryListController();
        BrewingHistoryListView bhlv = new BrewingHistoryListView(bhlc);
        bhlv.setVisible(true);
    }

    public void takeNote(){
        System.out.println("it cannot pass a index in a loop directly, maybe I need to use a list to do it");
        NoteInputController nic = new NoteInputController();
        NoteInputView niv = new NoteInputView(nic, 3);
        niv.setVisible(true);
    }
}
