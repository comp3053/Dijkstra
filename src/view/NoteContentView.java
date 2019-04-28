package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NoteContentView extends View {
    public NoteContentView(){
        this.setTitle("Note Content"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

        /* back button*/
        JButton btn_back = new JButton("back");
        jp_header.add(btn_back);
        JLabel msg_header = new JLabel("Brew Note " + "01" + "for brew history " + "01");
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.NORTH);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BorderLayout());
        JLabel msg_content = new JLabel("This is a note content");
        msg_content.setVerticalAlignment(1);
        jp_main.setBorder(new EmptyBorder(20,20,0,0));
        jp_main.add(msg_content,BorderLayout.CENTER);

        this.add(jp_main, BorderLayout.CENTER);
    }



    @Override
    public void update() {
        //repaint();
    }
}
