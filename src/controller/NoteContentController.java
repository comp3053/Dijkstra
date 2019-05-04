package controller;

import view.NoteListView;

public class NoteContentController {
    public NoteContentController(){

    }

    public String readNoteContent(){
        return "This is content";
    }

    public void goBack(){
        NoteListController nlc = new NoteListController();
        NoteListView nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }
}
