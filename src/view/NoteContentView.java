package view;

import controller.NoteContentController;
import model.Note;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NoteContentView extends View {
    private NoteContentController c;
    private Note m;

    public NoteContentView(NoteContentController c, Note m) {
        this.c = c;
        this.m = m;
        this.setTitle("Brew Day! - Note Detail"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout());

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Note " + m.getID() + " for Brew History " + m.getBrewID());
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        button.addActionListener(e -> {
            c.goBack();
            dispose();
        });

        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JLabel noteContent = new JLabel(m.getContent());
        noteContent.setVerticalAlignment(1);
        mainPanel.setBorder(new EmptyBorder(20, 20, 0, 0));
        mainPanel.add(noteContent, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
