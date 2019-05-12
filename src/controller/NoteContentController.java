package controller;

import view.NoteListView;

public class NoteContentController {
    public NoteContentController(){

    }

    public void goBack(){
        NoteListController nlc = new NoteListController();
        NoteListView nlv = null;
        nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }
}
