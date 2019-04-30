package view;

import controller.HomeController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends View {
    private HomeController hc;

    public HomeView(HomeController hc){
        this.hc = hc;
        this.setTitle("Brew Day! - Home"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        JButton btn1 = new JButton("Manage Recipe");
        JButton btn2 = new JButton("Manage Ingredient");
        JButton btn3 = new JButton("Note List");
        JButton btn4 = new JButton("Equipment Information");
        jp1.add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not implemented");
                // hc.startManageRecipe();
            }
        });
        jp1.add(btn2);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not implemented");
                //hc.startManageIngredient();
            }
        });
        jp1.add(btn3);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not implemented");
                //hc.startNoteList();
            }
        });
        jp1.add(btn4);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not implemented");
                //hc.startEquipmentInformation();
            }
        });
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
        recommend_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not implemented");
//                hc.startRecommend();
            }
        });
        this.add(jp2, BorderLayout.CENTER); // add a panel
        JPanel copyright = new JPanel();
        JLabel copyright_word = new JLabel("(c) Copyright 2019 Dijkstra.");
        copyright.add(copyright_word);
        this.add(copyright, BorderLayout.PAGE_END);
    }

    @Override
    public void update() {
        //repaint();
    }
}
