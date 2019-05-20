package view;

import controller.BrewRecipeController;
import model.Recipe;

import javax.swing.*;
import java.awt.*;

public class BrewRecipeView extends View {
    private BrewRecipeController c;
    private Recipe recipe;
    private double batchSize;

    /**
     * User interface for Brew a recipe.
     * @param c Controller for brewing a recipe.
     * @param recipe Recipe to brew.
     * @param batchSize Batch size you plan to brew.
     */
    public BrewRecipeView(BrewRecipeController c, Recipe recipe, double batchSize) {
        this.c = c;
        this.recipe = recipe;
        this.batchSize = batchSize;
        this.setTitle("Brew Day! - Brew Recipe"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout()); // Set BorderLayout to the frame
        JPanel jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        JPanel word = new JPanel();
        word.setLayout(new BorderLayout());
        JLabel title = new JLabel(this.recipe.getName()); // Recipe Name
        //greeting.setHorizontalAlignment(JLabel.CENTER); // Vertical central the label in BorderLayout
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        word.add(title, BorderLayout.LINE_START);
        JLabel batchSizeLabel = new JLabel("Batch Size: " + this.batchSize + " mL");
        word.add(batchSizeLabel, BorderLayout.LINE_END);
        jp2.add(word, BorderLayout.PAGE_START);
        this.add(jp2, BorderLayout.PAGE_START);

        // Show the used ingredient for this time brew to user
        String[] columnNames = {"Ingredient", "Unit", "Amount"};
        // Automatically get the name, unit and amount of used recipe ingredient
        Object[][] data = new Object[recipe.getIngredients().size()][3];
        recipe.getIngredients().forEach(ingredient -> {
            int index = recipe.getIngredients().indexOf(ingredient);
            data[index][0] = ingredient.getName();
            data[index][1] = ingredient.getUnit();
            data[index][2] = ingredient.getAmount();
        });

        JTable table = new JTable(data, columnNames);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Finish");
        footerPanel.add(saveBtn);
        saveBtn.addActionListener(e -> {
            c.finish();
            dispose();
        });
        this.add(footerPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void update() {
        // Nothing to do
    }
}
