package controller;

import view.HomeView;

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

    public void delete(){
        System.out.println("delete");
    }

    public void addNote(){

    }
}
