package view;

import controller.FetchDataException;
import controller.NoteContentController;
import controller.NoteController;
import controller.ObjectNotFoundException;
import model.Note;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteContentView extends View {
    private NoteContentController c;
    private NoteController nc;
    private Note note;

    public NoteContentView(NoteContentController c, int noteID){
        this.nc = new NoteController();
        this.c = c;
        this.setTitle("Brew Day! - Note Detail"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());
        try {
            this.note = nc.getNote(noteID);
        } catch (FetchDataException | ObjectNotFoundException e) {
            e.printStackTrace();
            this.note = new Note(0, "Could not find the note.");
        }

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Brew Note " + this.note.getID() + " for brew history " + this.note.getBrewID());
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
            }
        });

        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JLabel msg_content = new JLabel(this.note.getContent());
        msg_content.setVerticalAlignment(1);
        mainPanel.setBorder(new EmptyBorder(20,20,0,0));
        mainPanel.add(msg_content,BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
