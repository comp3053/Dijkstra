package view;

import controller.NoteInputController;
import model.Note;
import utils.FetchDataException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteInputView extends View{
    private NoteInputController c;
    private Note m;
    public NoteInputView(NoteInputController c, Note m){
        this.c = c;
        this.m = m;
        this.setTitle("Brew Day! - Edit Note"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Writing Note for Brewing History " + m.getBrewID());
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (c.backToNoteList() == 1){
                        dispose();
                    }
                } catch (FetchDataException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.add(topLeftButtonBar, BorderLayout.PAGE_START);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(0,20,10,20)); // top and left padding for recommend entry

        JTextArea input_noteContent = new JTextArea(m.getContent());
        mainPanel.add(input_noteContent,BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        bottomLeftButtonBar.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean insertSuccess = c.saveNote(m,input_noteContent.getText());
                if(insertSuccess){
                    JOptionPane.showMessageDialog(null,"Your note have been saved","Success",JOptionPane.PLAIN_MESSAGE);
                    dispose();}else{
                    JOptionPane.showMessageDialog(null,"Error!","Error",JOptionPane.ERROR_MESSAGE);
                }
                //TODO: Add operation to show status of insert
            }
        });
        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }


    @Override
    public void update() {
        //repaint();
    }
}
