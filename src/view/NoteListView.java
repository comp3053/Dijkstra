package view;

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
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.addNote();
            }
        });

        this.add(topButtonsAround, BorderLayout.NORTH);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BoxLayout(jp_main,BoxLayout.Y_AXIS));
        for(int i =0;i<5;i++) {
            JPanel jp_main_i = new JPanel();
            jp_main_i.setLayout(new FlowLayout());
            JButton btn_notei = new JButton("brew history" + "03");
            jp_main_i.add(btn_notei);
            JLabel msg_relative = new JLabel("for brewing record");
            jp_main_i.add(msg_relative);
            JButton btn_delete = new JButton("delete");
            btn_delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.delete();
                }
            });
            jp_main_i.add(btn_delete);
            jp_main.add(jp_main_i);
        }
        this.add(jp_main, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
