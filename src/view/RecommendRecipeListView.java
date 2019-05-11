package view;

import controller.RecommendRecipeListController;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecommendRecipeListView extends View{
    private RecommendRecipeListController c;
    private ArrayList<Recipe> recommendRecipe;

    // viewStatus, false: enough ingredient, true: not enough
    public RecommendRecipeListView(RecommendRecipeListController c, ArrayList<Recipe> recommendRecipe, boolean viewStatus){
        this.c = c;
        this.recommendRecipe = recommendRecipe;
        this.setTitle("Brew Day! - Recommend Recipe List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Recommend Recipes");
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());
        if (viewStatus){
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
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        ButtonGroup bg = new ButtonGroup();
        for(Recipe recommendRecipeItem: recommendRecipe) {
            JRadioButton ingredientItem = new JRadioButton(recommendRecipeItem.getName() + ": "+ recommendRecipeItem.getIngredients().size()+ " Ingredient in used");
            ingredientItem.setActionCommand(String.valueOf(recommendRecipeItem.getID()));
            bg.add(ingredientItem);
            mainPanel.add(ingredientItem);
        }
        // TODO: Need to change to scrollable
        this.add(mainPanel, BorderLayout.CENTER);


        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        if (viewStatus){
            JButton generateListBtn = new JButton("Generate Shopping List");
            bottomLeftButtonBar.add(generateListBtn);
            generateListBtn.addActionListener(e -> {
                try {
                    for (Recipe recipe : recommendRecipe){
                        if (recipe.getID() == Integer.valueOf(bg.getSelection().getActionCommand())){
                            c.generateShoppingList(recipe);
                        }
                    }

                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Please select a recipe.");
                    return;
                }
                dispose();
            });
        }else {
            JButton brewButton = new JButton("Brew this recipe");
            bottomLeftButtonBar.add(brewButton);
            brewButton.addActionListener(e -> {
                try {
                    for (Recipe recipe : recommendRecipe){
                        if (recipe.getID() == Integer.valueOf(bg.getSelection().getActionCommand())){
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
