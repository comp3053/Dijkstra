package view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        this.setTitle("Brew Day!"); // set frame title
        this.setSize(800, 600); // set frame size
        BorderLayout bl1 = new BorderLayout();
        this.setLayout(bl1); // set borderlayout to the frame

        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        JButton btn1 = new JButton("Manage Recipe");
        JButton btn2 = new JButton("Manage Ingredient");
        JButton btn3 = new JButton("Note List");
        JButton btn4 = new JButton("Equipment Information");
        jp1.add(btn1);
        jp1.add(btn2);
        jp1.add(btn3);
        jp1.add(btn4);
        this.add(jp1, BorderLayout.PAGE_START); // add two button

        JPanel jp2 = new JPanel();
        jp2.setBorder(new EmptyBorder(100,50,0,0)); // top and left padding for recommend entry
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));// Vertically display
        JLabel greeting = new JLabel("Hello");
        //greeting.setHorizontalAlignment(JLabel.CENTER); // Vertical central the label in BorderLayout
        // Set Font size
        greeting.setFont(new Font(greeting.getFont().getFontName(), greeting.getFont().getStyle(), 48));

        JLabel help_word = new JLabel("I can recommend a brew for you.");
        //help_word.setHorizontalAlignment(JLabel.CENTER);
        // Set Font size
        help_word.setFont(new Font(help_word.getFont().getFontName(), help_word.getFont().getStyle(), 24));
        JButton recommend_btn = new JButton("Recommend");

        jp2.add(greeting);
        jp2.add(help_word);
        jp2.add(recommend_btn);
        this.add(jp2, BorderLayout.CENTER); // add a panel

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
