package controller;

import view.NoteListView;

public class NoteContentController {
    public NoteContentController() {
        // Nothing to do
    }

    /**
     * Go back to NoteListView.
     */
    public void goBack() {
        NoteListController nlc = new NoteListController();
        NoteListView nlv;
        nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }
}
