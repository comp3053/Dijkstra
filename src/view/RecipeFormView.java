package view;

import controller.RecipeFormController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RecipeFormView extends View {
    private RecipeFormController c;
    public RecipeFormView(RecipeFormController c){
        this.c = c;
        this.setTitle("Brew Day! - Recipe Form"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame

        JPanel pageTitle = new JPanel();
        pageTitle.setLayout(new BoxLayout(pageTitle, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Recipe Form");
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        pageTitle.add(title);
        this.add(pageTitle, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel recipeNameField = new JPanel();
        recipeNameField.setLayout(new FlowLayout());
        JLabel recipe_name_title = new JLabel("Recipe Name:");
        recipeNameField.add(recipe_name_title);
        JTextField recipeNameTextfield = new JTextField();
        recipeNameTextfield.setColumns(20);
        recipeNameField.add(recipeNameTextfield);
        mainPanel.add(recipeNameField);
        this.add(mainPanel);

        // TODO: Ingredients Field

        JPanel recipeBatchSize = new JPanel();
        recipeBatchSize.setLayout(new FlowLayout());
        JLabel recipeBatchSizeTitle = new JLabel("Batch Size:");
        recipeBatchSize.add(recipeBatchSizeTitle);
        JTextField recipeBatchSizeTextfield = new JTextField();
        recipeBatchSizeTextfield.setColumns(20);
        recipeBatchSize.add(recipeBatchSizeTextfield);
        mainPanel.add(recipeBatchSize);
        this.add(mainPanel);

        JPanel pageEndButtonGroup = new JPanel();
        pageEndButtonGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        pageEndButtonGroup.add(saveBtn);
        pageEndButtonGroup.add(cancelBtn);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.saveRecipe();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.cancel();
            }
        });
        this.add(pageEndButtonGroup, BorderLayout.PAGE_END);
    }

    @Override
    public void update(){

    }
}
