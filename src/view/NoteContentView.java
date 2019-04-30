package view;

import controller.NoteContentController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteContentView extends View {
    private NoteContentController c;
    public NoteContentView(NoteContentController c){
        this.c = c;
        this.setTitle("Brew Day! - Note Detail"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Brew Note " + "01" + " for brew history " + "01");
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
            }
        });

        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

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
