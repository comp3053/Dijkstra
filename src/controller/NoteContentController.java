package controller;

import utils.FetchDataException;
import view.NoteListView;
import model.Note;

public class NoteContentController {
    public NoteContentController(){

    }

    public String readNoteContent(){
        return "This is content";
    }

    public void goBack(){
        NoteListController nlc = new NoteListController();
        NoteListView nlv = null;
        nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }
}
