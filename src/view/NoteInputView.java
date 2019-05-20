package view;

import controller.NoteInputController;
import model.Note;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NoteInputView extends View {
    private NoteInputController c;
    private Note m;

    /**
     * User interface to edit note.
     * @param c Controller for note editing.
     * @param m Note to edit.
     */
    public NoteInputView(NoteInputController c, Note m) {
        this.c = c;
        this.m = m;
        this.setTitle("Brew Day! - Edit Note"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout());

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton BackButton = new JButton("< Back");
        topLeftButtonBar.add(BackButton);
        JLabel headerTitle = new JLabel("Writing Note for Brewing History " + m.getBrewID());
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        BackButton.addActionListener(e -> {
            boolean result_Leave = false;
            int isSave = JOptionPane.showConfirmDialog(null, "Are you sure to leave without saving your note?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (isSave == 0)
                result_Leave = c.backToNoteList();
            if (result_Leave) {
                dispose();
            }
        });
        this.add(topLeftButtonBar, BorderLayout.PAGE_START);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(0, 20, 10, 20)); // top and left padding for recommend entry

        JTextArea input_noteContent = new JTextArea(m.getContent());
        input_noteContent.setLineWrap(true); //allow enter automatically
        input_noteContent.setWrapStyleWord(true);
        JScrollPane scrollPaneForNoteInput = new JScrollPane(input_noteContent);
        mainPanel.add(scrollPaneForNoteInput, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        bottomLeftButtonBar.add(saveButton);

        // Setup action for save button
        saveButton.addActionListener(e -> {
            // Check if the input of note is empty.
            if (input_noteContent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The content cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean insertSuccess = c.saveNote(m, input_noteContent.getText());

                if (insertSuccess) {
                    // The note has successfully inserted.
                    JOptionPane.showMessageDialog(null, "Your note have been saved", "Success", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                } else {
                    // Some errors happened in note insert.
                    JOptionPane.showMessageDialog(null, "Error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }


    @Override
    public void update() {
        //repaint();
    }
}
