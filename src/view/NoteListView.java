package view;

import controller.NoteContentController;
import controller.NoteListController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteListView extends View {
    private NoteListController c;
    public NoteListView(NoteListController c){
        this.c = c;
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
        for(int i =0;i<5;i++) {
            JPanel mainPanelIter = new JPanel();
            mainPanelIter.setLayout(new FlowLayout());
            JButton noteBtn = new JButton("brew history" + "03");
            mainPanelIter.add(noteBtn);
            JLabel forBrewingRecord = new JLabel("for brewing record");
            mainPanelIter.add(forBrewingRecord);
            JButton modifyBtn = new JButton("modify");
            JButton deleteBtn = new JButton("delete");
            noteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NoteContentController ncc = new NoteContentController();
                    NoteContentView ncv = new NoteContentView(ncc, 1);
                    // TODO: Need to change according to click (better pass in a model)
                    ncv.setVisible(true);
                    dispose();
                }
            });
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.delete();
                }
            });
            modifyBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    TODO: Modify Note
//                    c.modify();
//                    dispose();
                }
            });
            mainPanelIter.add(modifyBtn);
            mainPanelIter.add(deleteBtn);
            mainPanel.add(mainPanelIter);
        }
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
