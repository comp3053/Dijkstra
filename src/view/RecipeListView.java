package view;

import controller.RecipeListController;
import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecipeListView extends View {
    private RecipeListController c;
    private JPanel listPanel;
    private JLabel subtitle;

    private ArrayList<Recipe> recipes;

    /**
     * User interface of RecipeList.
     * @param c Controller of current view.
     * @param recipes All the recipes in the recipe list.
     */
    public RecipeListView(RecipeListController c, ArrayList<Recipe> recipes) {
        this.c = c;
        this.recipes = recipes;
        this.setTitle("Brew Day! - Manage Recipe"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout()); // Set BorderLayout to the frame

        JPanel topButtonsAround = new JPanel();
        topButtonsAround.setLayout(new BoxLayout(topButtonsAround, BoxLayout.LINE_AXIS));

        JButton leftButton = new JButton("< Back");
        JButton rightButton = new JButton("New");

        topButtonsAround.add(leftButton);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(rightButton);

        leftButton.addActionListener(e -> {
            c.goBack();
            dispose();
        });

        rightButton.addActionListener(e -> {
            if (c.newRecipe()){
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "There should be at least one ingredient before create new recipe!");
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

        subtitle = new JLabel(recipes.size() + " Recipes in the database");
        //help_word.setHorizontalAlignment(JLabel.CENTER);
        // Set Font size
        subtitle.setFont(new Font(subtitle.getFont().getFontName(), subtitle.getFont().getStyle(), 16));
        word.add(title);
        word.add(subtitle);
//        word.setBorder(new EmptyBorder(0,0,0,0));
        mainPanel.add(word, BorderLayout.PAGE_START);
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        createRecipeList(recipes);

        mainPanel.add(listPanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Create recipe list according to the model.
     * @param recipes Recipe you use to create the recipe list.
     * @return The length of recipe list.
     */
    private int createRecipeList(ArrayList<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            JPanel listPanelIter = new JPanel();
            listPanelIter.setLayout(new FlowLayout());
            JLabel nameLabel = new JLabel(recipe.getName());
            listPanelIter.add(nameLabel);
            JButton detailBtn = new JButton("detail");
            JButton editBtn = new JButton("edit");
            JButton deleteBtn = new JButton("delete");
            recipe.addListener(this);

            detailBtn.addActionListener(e -> {
                c.recipeDetail(recipe);
                dispose();
            });
            editBtn.addActionListener(e -> {
                c.editRecipe(recipe);
                dispose();
            });
            deleteBtn.addActionListener(e -> {
                int isDelete = JOptionPane.showConfirmDialog(null,
                        String.format("You are going to delete recipe %s", recipe.getName()),
                        "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (isDelete == JOptionPane.YES_OPTION) {
                    boolean status = recipe.delete();
                    if (!status) {
                        JOptionPane.showMessageDialog(null,
                                String.format("Delete %s failed.", recipe.getName()));
                    }
                }
            });
            listPanelIter.add(detailBtn);
            listPanelIter.add(editBtn);
            listPanelIter.add(deleteBtn);
            listPanel.add(listPanelIter);
        }
        return recipes.size();
    }

    @Override
    public void update() {
        listPanel.removeAll();
        listPanel.repaint();
        try {
            int size = createRecipeList(Recipe.getAll());
            subtitle.setText(size + " Recipes in the database");
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
        listPanel.revalidate();
    }
}
