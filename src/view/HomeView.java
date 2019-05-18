package view;

import controller.HomeController;
import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class HomeView extends View {
    private HomeController hc;
    private ArrayList<Recipe> recommendRecipes;

    public HomeView(HomeController hc) {
        this.hc = hc;

        try {
            this.recommendRecipes = Recipe.getAll();
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }

        this.setTitle("Brew Day! - Home"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout()); // Set BorderLayout to the frame
        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        JButton btn1 = new JButton("Manage Recipe");
        JButton btn2 = new JButton("Manage Ingredient");
        JButton btn3 = new JButton("Note List");
        JButton btn4 = new JButton("Equipment Information");

        // Setup ManageRecipe button
        jp1.add(btn1);
        btn1.addActionListener(e -> {
            hc.startManageRecipe();
            dispose();
        });

        // Setup ManageIngredient button
        jp1.add(btn2);
        btn2.addActionListener(e -> {
            hc.startManageIngredient();
            dispose();
        });

        // Setup NoteList button
        jp1.add(btn3);
        btn3.addActionListener(e -> {
            hc.startNoteList();
            dispose();
        });

        // Setup EquipmentInformation button
        jp1.add(btn4);
        btn4.addActionListener(e -> {
            hc.startEquipmentInformation();
            dispose();
        });
        this.add(jp1, BorderLayout.PAGE_START); // add two button

        JPanel jp2 = new JPanel();
        jp2.setBorder(new EmptyBorder(100, 50, 0, 0)); // Top and left padding for recommend entry
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
        recommend_btn.addActionListener(e -> {
            //Judge if there is any recommend recipe,give a warning
            if (this.recommendRecipes.size()>0) {
                hc.startRecommend();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "There should have at least one recipe to start recommend recipe!");
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
