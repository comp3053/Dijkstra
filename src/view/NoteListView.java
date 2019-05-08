package view;

import controller.NoteContentController;
import controller.NoteListController;
import model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NoteListView extends View {
    private NoteListController c;
    private ArrayList<Note> notes;

    public NoteListView(NoteListController c){
        this.c = c;
        this.notes = c.readNoteList();
        this.setTitle("Brew Day! - Note List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel topButtonsAround = new JPanel();
        topButtonsAround.setLayout(new BoxLayout(topButtonsAround, BoxLayout.LINE_AXIS));

        JButton leftButton = new JButton("< Back");
        JButton rightButton = new JButton("Add Note");
        JLabel headerTitle = new JLabel("Note List");
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));

        topButtonsAround.add(leftButton);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(headerTitle);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(rightButton);

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
                dispose();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.addNote();
                dispose();
            }
        });

        this.add(topButtonsAround, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        for(Note note:notes) {
            JPanel mainPanelIter = new JPanel();
            mainPanelIter.setLayout(new FlowLayout());
            JLabel brewHistory = new JLabel("Brew History " + note.getID());
            mainPanelIter.add(brewHistory);
            JLabel forBrewingRecord = new JLabel("for Brewing History " + note.getBrewID());
            mainPanelIter.add(forBrewingRecord);
            JButton detailBtn = new JButton("detail");
            JButton modifyBtn = new JButton("modify");
            JButton deleteBtn = new JButton("delete");
            detailBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NoteContentController ncc = new NoteContentController();
                    NoteContentView ncv = new NoteContentView(ncc,note);
                    // TODO: Need to change according to click (better pass in a model)
                    ncv.setVisible(true);
                    dispose();
                }
            });
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.delete(note);
                }
            });
            modifyBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.modify(note);
                    dispose();
                }
            });
            mainPanelIter.add(detailBtn);
            mainPanelIter.add(modifyBtn);
            mainPanelIter.add(deleteBtn);
            mainPanel.add(mainPanelIter);
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(mainPanel);
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
