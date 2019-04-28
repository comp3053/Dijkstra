package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NoteInputView extends View{
    public NoteInputView(){
        this.setTitle("Note Input"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        /* back button*/
        JButton btn_back = new JButton("back to note list");
        btn_back.setBounds(50,50,100,50);
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Add note");
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.PAGE_START);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
        JLabel msg_noteInfo = new JLabel("Tasting note for brewing history" + "01");
        jp_main.add(msg_noteInfo);
        JTextField input_noteContent = new JTextField("Please write down your note");
        jp_main.add(input_noteContent);

        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        JButton btn_save = new JButton("save");
        jp_foot.add(btn_save);
        this.add(jp_foot, BorderLayout.LINE_END);


    }


    @Override
    public void update() {
        //repaint();
    }
}
