package controller;

import view.BrewingHistoryListView;
import view.HomeView;
import view.NoteInputView;

public class NoteListController {
    public NoteListController(){

    }

    public void goBack(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void readNoteList(){

    }

    public void modify(){
        System.out.println("modify");
        // TODO, Fetch the id and modify the note
    }

    public void delete(){
        System.out.println("delete");
        // TODO, Fetch the id and do the deletion
    }

    public void addNote(){
        BrewingHistoryListController bhlc = new BrewingHistoryListController();
        BrewingHistoryListView bhlv = new BrewingHistoryListView(bhlc);
        bhlv.setVisible(true);
    }
}
