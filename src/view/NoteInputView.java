package view;

import controller.NoteInputController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteInputView extends View{
    private NoteInputController c;
    public NoteInputView(NoteInputController c, int brewID){
        this.c = c;
        this.setTitle("Brew Day! - Edit Note"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Writing note for brewing history " + brewID);
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        button.addActionListener(e -> {
            if (c.backToNoteList() == 1){
                dispose();
            }
        });
        this.add(topLeftButtonBar, BorderLayout.PAGE_START);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(0,20,10,20)); // top and left padding for recommend entry

        JTextArea input_noteContent = new JTextArea("Please write down your note here");
        mainPanel.add(input_noteContent,BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        bottomLeftButtonBar.add(saveButton);
        saveButton.addActionListener(e -> {
            c.saveNote(brewID, input_noteContent.getText());
            //TODO: Add operation to show status of insert
        });
        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }


    @Override
    public void update() {
        //repaint();
    }
}
