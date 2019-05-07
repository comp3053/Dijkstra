package view;

import utils.FetchDataException;
import controller.RecipeController;
import controller.RecipeListController;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecipeListView extends View{
    private RecipeListController c;
    private RecipeController rc;
    private ArrayList<Recipe> recipe;
    public RecipeListView(RecipeListController c){
        this.c = c;
        this.rc = new RecipeController();
        this.setTitle("Brew Day! - Manage Recipe"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        try {
            this.recipe = rc.getAll();
        } catch (FetchDataException e) {
            e.printStackTrace();
        }

        JPanel topButtonsAround = new JPanel();
        topButtonsAround.setLayout(new BoxLayout(topButtonsAround, BoxLayout.LINE_AXIS));

        JButton leftButton = new JButton("< Back");
        JButton rightButton = new JButton("New");

        topButtonsAround.add(leftButton);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(rightButton);

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
                dispose();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.newRecipe();
                dispose();
            }
        });
        this.add(topButtonsAround, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel word = new JPanel();
        word.setLayout(new BoxLayout(word, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Recipes List");
        //greeting.setHorizontalAlignment(JLabel.CENTER); // Vertical central the label in BorderLayout
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));

        JLabel subtitle = new JLabel(recipe.size()+" Recipes in the database");
        //help_word.setHorizontalAlignment(JLabel.CENTER);
        // Set Font size
        subtitle.setFont(new Font(subtitle.getFont().getFontName(), subtitle.getFont().getStyle(), 16));
        word.add(title);
        word.add(subtitle);
//        word.setBorder(new EmptyBorder(0,0,0,0));
        mainPanel.add(word, BorderLayout.PAGE_START);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        for (Recipe value : recipe) {
            JPanel listPanelIter = new JPanel();
            listPanelIter.setLayout(new FlowLayout());
            JLabel nameLabel = new JLabel(value.getName());
            listPanelIter.add(nameLabel);
            JButton detailBtn = new JButton("detail");
            JButton editBtn = new JButton("edit");
            JButton deleteBtn = new JButton("delete");
            int recipeID = value.getID();
            detailBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.recipeDetail(recipeID);
                    dispose();
                }
            });
            editBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO: Edit ingredient
                }
            });
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO: Delete ingredient
                }
            });
            listPanelIter.add(detailBtn);
            listPanelIter.add(editBtn);
            listPanelIter.add(deleteBtn);
            listPanel.add(listPanelIter);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // TODO: Warning pop up when hitting delete
//        mainPanel.add(listPanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
