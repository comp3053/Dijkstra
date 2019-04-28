package view;

import javax.swing.*;
import java.awt.*;

public class NoteListView extends View {
    public NoteListView(){
        this.setTitle("Note List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        /* back button*/
        JButton btn_back = new JButton("back");
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Note List");
        jp_header.add(msg_header);

        JButton btn_addNote = new JButton("Add Note");
        jp_header.add(btn_addNote);

        this.add(jp_header, BorderLayout.PAGE_START);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
        for(int i =0;i<5;i++) {
            JButton btn_notei = new JButton("brew history" + "03");
            jp_main.add(btn_notei);
            JLabel msg_relative = new JLabel("for brewing record");
            jp_main.add(msg_relative);
            JButton btn_delete = new JButton("delete");
            jp_main.add(btn_delete);
        }
        this.add(jp_main, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
