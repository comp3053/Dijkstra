package view;

import controller.NoteContentController;
import controller.NoteListController;
import model.Note;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NoteListView extends View {
    private NoteListController c;
    private JPanel mainPanel;

    /**
     * List of all the notes in database.
     * @param c Controller for NoteListView.
     */
    public NoteListView(NoteListController c) {
        this.c = c;
        this.mainPanel = new JPanel();
        this.setTitle("Brew Day! - Note List"); // Set frame title
        this.setSize(800, 600); // Set frame size
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

        leftButton.addActionListener(e -> {
            c.goBack();
            dispose();
        });

        rightButton.addActionListener(e -> {
            c.addNote();
            dispose();
        });

        this.add(topButtonsAround, BorderLayout.NORTH);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        createNoteList(c.readNoteList());
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(mainPanel);
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * Create list of all notes exist in database.
     * @param notes All the notes in database.
     */
    private void createNoteList(ArrayList<Note> notes) {
        for (Note note : notes) {
            note.addListener(this);
            JPanel mainPanelIter = new JPanel();
            mainPanelIter.setLayout(new FlowLayout());
            JLabel brewHistory = new JLabel("Note " + note.getID());
            mainPanelIter.add(brewHistory);
            JLabel forBrewingRecord = new JLabel("for Brewing History " + note.getBrewID());
            mainPanelIter.add(forBrewingRecord);
            JButton detailBtn = new JButton("detail");
            JButton modifyBtn = new JButton("modify");
            JButton deleteBtn = new JButton("delete");
            detailBtn.addActionListener(e -> {
                NoteContentController ncc = new NoteContentController();
                NoteContentView ncv = new NoteContentView(ncc, note);
                ncv.setVisible(true);
                dispose();
            });
            deleteBtn.addActionListener(e -> {
                int isSave = JOptionPane.showConfirmDialog(null, "Are you sure to delete your note?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (isSave == 0) {
                    if (c.delete(note))
                        JOptionPane.showMessageDialog(null, "Your note has been deleted", "Success", JOptionPane.PLAIN_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Error!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Your note has not been deleted ", "Success", JOptionPane.ERROR_MESSAGE);
                }
            });
            modifyBtn.addActionListener(e -> {
                c.modify(note);
                dispose();
            });
            mainPanelIter.add(detailBtn);
            mainPanelIter.add(modifyBtn);
            mainPanelIter.add(deleteBtn);
            mainPanel.add(mainPanelIter);
        }
    }

    @Override
    public void update() {
        mainPanel.removeAll();
        mainPanel.repaint();
        try {
            createNoteList(c.readNoteList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainPanel.revalidate();
    }
}
