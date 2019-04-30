package view;

import controller.NoteInputController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteInputView extends View{
    private NoteInputController c;
    public NoteInputView(NoteInputController c){
        this.c = c;
        this.setTitle("Note Input"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

        /* back button*/
        JButton btn_back = new JButton("back to note list");
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.backToNoteList();
            }
        });
        jp_header.add(btn_back);

        /* This part change the design */
        JLabel msg_header = new JLabel("Add note for brewing history" + "01");
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.NORTH);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BorderLayout());
        jp_main.setBorder(new EmptyBorder(0,20,10,20)); // top and left padding for recommend entry

        JTextArea input_noteContent = new JTextArea("Please write down your note here");
        jp_main.add(input_noteContent,BorderLayout.CENTER);

        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btn_save = new JButton("save");
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.saveNote(input_noteContent.getText());
            }
        });
        jp_foot.add(btn_save);
        this.add(jp_foot, BorderLayout.SOUTH);
    }


    @Override
    public void update() {
        //repaint();
    }
}
