package view;

import controller.RecommendRecipeListController;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecommendRecipeListView extends View {
    private RecommendRecipeListController c;
    private ArrayList<Recipe> recommendRecipes;

    /**
     * User interface for RecommendRecipe.
     * @param c Controller for current view.
     * @param recommendRecipes Recipes which can brew now.
     * @param viewStatus true: There is no recipe which could brew now. false: Recipes could brew exist.
     */
    public RecommendRecipeListView(RecommendRecipeListController c, ArrayList<Recipe> recommendRecipes, boolean viewStatus) {
        this.c = c;
        this.recommendRecipes = recommendRecipes;
        this.setTitle("Brew Day! - Recommend Recipe List"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout());

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Recommend Recipes");
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());
        if (viewStatus) {
            JPanel missingLabel = new JPanel();
            missingLabel.setBackground(Color.YELLOW);
            JLabel missingAlert = new JLabel("Ingredient Not Enough");
            missingLabel.add(missingAlert);
            topLeftButtonBar.add(missingLabel);
        }

        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        button.addActionListener(e -> {
            c.goBack();
            dispose();
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        ButtonGroup bg = new ButtonGroup();
        // Display all corresponding recipes and the amount of the ingredient will used in the table
        for (Recipe recommendRecipeItem : recommendRecipes) {
            JRadioButton ingredientItem = new JRadioButton(recommendRecipeItem.getName() + ": " + recommendRecipeItem.getIngredients().size() + " Ingredient in used");
            ingredientItem.setActionCommand(String.valueOf(recommendRecipeItem.getID()));
            bg.add(ingredientItem);
            mainPanel.add(ingredientItem);
        }
        this.add(new JScrollPane(mainPanel), BorderLayout.CENTER);


        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

        /*
         * Different view status will generate different button.
         * True -> Generate shopping list
         * False -> brew recipe
         */
        if (viewStatus) {
            JButton generateListBtn = new JButton("Generate Shopping List");
            bottomLeftButtonBar.add(generateListBtn);
            generateListBtn.addActionListener(e -> {
                try {
                    //get the selected recipe to generate shopping list
                    for (Recipe recipe : recommendRecipes) {
                        if (recipe.getID() == Integer.valueOf(bg.getSelection().getActionCommand())) {
                            c.generateShoppingList(recipe, recommendRecipes);
                        }
                    }

                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Please select a recipe.");
                    return;
                }
                dispose();
            });
        } else {
            JButton brewButton = new JButton("Brew this recipe");
            bottomLeftButtonBar.add(brewButton);
            brewButton.addActionListener(e -> {
                try {
                    // Get the selected recipe to generate brew detail
                    for (Recipe recipe : recommendRecipes) {
                        if (recipe.getID() == Integer.valueOf(bg.getSelection().getActionCommand())) {
                            c.brewRecipe(recipe);
                        }
                    }
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Please select a recipe.");
                    return;
                }
                dispose();
            });
        }


        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }

    @Override
    public void update() {
        //repaint();
    }
}
