package view;


import controller.RecipeDetailController;
import model.Recipe;

import javax.swing.*;
import java.awt.*;

public class RecipeDetailView extends View {
    private RecipeDetailController c;
    private Recipe recipe;

    public RecipeDetailView(RecipeDetailController c, Recipe recipe) {
        this.c = c;
        this.setTitle("Brew Day! - Recipe Detail"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout()); // Set BorderLayout to the frame
        this.recipe = recipe;

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);

        button.addActionListener(e -> {
            c.goBack();
            dispose();
        });

        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel pageTitle = new JPanel();
        pageTitle.setLayout(new BoxLayout(pageTitle, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Recipe detail");
        //greeting.setHorizontalAlignment(JLabel.CENTER); // Vertical central the label in BorderLayout
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        JLabel subtitle = new JLabel(this.recipe.getName() + "(for an 1000mL batch size brew)");
        subtitle.setFont(new Font(subtitle.getFont().getFontName(), subtitle.getFont().getStyle(), 16));
        pageTitle.add(title);
        pageTitle.add(subtitle);
        mainPanel.add(pageTitle, BorderLayout.PAGE_START);

        JPanel recipeDetailPanel = new JPanel();
        recipeDetailPanel.setLayout(new BoxLayout(recipeDetailPanel, BoxLayout.Y_AXIS));

        // Get all the ingredients in recipe and print them out.
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            JLabel ingredient = new JLabel(this.recipe.getIngredients().get(i).getName() + ": "
                    + this.recipe.getIngredients().get(i).getAmount() + " - Unit: "
                    + this.recipe.getIngredients().get(i).getUnit().name());
            recipeDetailPanel.add(ingredient);
        }
//        JPanel description = new JPanel();
//        description.setLayout(new FlowLayout());
//        JLabel descLabel = new JLabel("Description:");
//        description.add(descLabel);
//        recipeDetailPanel.add(description);
//        mainPanel.add(recipeDetailPanel, BorderLayout.CENTER);

        JPanel recipePanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(recipePanel);
        recipePanel.setLayout(groupLayout);// Vertically display
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel ingredientLabel = new JLabel("Ingredient(s):");
        JLabel descriptionLabel = new JLabel("Description:");

        JLabel desc = new JLabel(recipe.getDescription());


        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();

        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(ingredientLabel)
                .addComponent(descriptionLabel));
        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(recipeDetailPanel)
                .addComponent(desc));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();

        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(ingredientLabel).addComponent(recipeDetailPanel));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(descriptionLabel).addComponent(desc));

        groupLayout.setVerticalGroup(vGroup);

        mainPanel.add(recipePanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {

    }
}
