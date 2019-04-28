package view;

import javax.swing.*;
import java.awt.*;

public class NoteContentView extends View {
    public NoteContentView(){
        this.setTitle("Note Content"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        /* back button*/
        JButton btn_back = new JButton("back");
        btn_back.setBounds(50,50,100,50);
        jp_header.add(btn_back);
        JLabel msg_header = new JLabel("Brew Note " + "01" + "for brew history " + "01");
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.PAGE_START);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
        JLabel msg_content = new JLabel("This is a note content");
        jp_main.add(msg_content);

        this.add(jp_main, BorderLayout.CENTER);
    }



    @Override
    public void update() {
        //repaint();
    }
}
