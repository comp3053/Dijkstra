package view;

import controller.RecipeFormController;
import model.RecipeForm;
import utils.DuplicateObjectException;

import javax.swing.*;
import java.awt.*;


public class RecipeFormView extends View {
    private RecipeFormController c;
    private RecipeForm m;

    public RecipeFormView(RecipeFormController c, RecipeForm m) {
        this.c = c;
        this.m = m;
        this.setTitle("Brew Day! - Recipe Form"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout()); // Set BorderLayout to the frame

        JPanel pageTitle = new JPanel();
        pageTitle.setLayout(new BoxLayout(pageTitle, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Recipe Form");
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        pageTitle.add(title);
        this.add(pageTitle, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel recipeNameField = new JPanel();
        recipeNameField.setLayout(new FlowLayout());
        JLabel recipe_name_title = new JLabel("Recipe Name:");
        recipeNameField.add(recipe_name_title);
        JTextField recipeNameTextfield = new JTextField();
        recipeNameTextfield.setColumns(20);
        recipeNameField.add(recipeNameTextfield);
        mainPanel.add(recipeNameField, BorderLayout.PAGE_START);
        this.add(mainPanel);

        RecipeIngredientEntryList recipeIngredientEntryList = new RecipeIngredientEntryList(m.getStorageIngredients());
        JScrollPane scrollPane = new JScrollPane(recipeIngredientEntryList);
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(recipeIngredientEntryList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonRecipeForm = new JPanel();
        buttonRecipeForm.setLayout(new BorderLayout());
        JPanel recipeBatchSize = new JPanel();
        recipeBatchSize.setLayout(new FlowLayout());
        JLabel recipeBatchSizeTitle = new JLabel("Batch Size (Unit - mL):");
        recipeBatchSize.add(recipeBatchSizeTitle);
        JTextField recipeBatchSizeTextfield = new JTextField();
        recipeBatchSizeTextfield.setColumns(5);
        recipeBatchSize.add(recipeBatchSizeTextfield);
        buttonRecipeForm.add(recipeBatchSize, BorderLayout.PAGE_START);

        JPanel recipeDescription = new JPanel();
        recipeDescription.setLayout(new FlowLayout());
        JLabel recipeDescriptionTitle = new JLabel("Recipe Description:");
        recipeDescription.add(recipeDescriptionTitle);
        JTextArea recipeDescriptionTextArea = new JTextArea(5, 30);
        recipeDescription.add(recipeDescriptionTextArea);
        buttonRecipeForm.add(recipeDescription, BorderLayout.PAGE_END);

        mainPanel.add(buttonRecipeForm, BorderLayout.PAGE_END);
        this.add(mainPanel);

        if (m.getRecipe().getID() > 0) {
            recipeNameTextfield.setText(m.getRecipe().getName());
            recipeIngredientEntryList.initIngredients(m.getRecipeIngredients());
            recipeDescriptionTextArea.setText(m.getRecipe().getDescription());
            recipeBatchSizeTextfield.setText("1000");
        } else {
            recipeIngredientEntryList.initForm();
        }
        JPanel pageEndButtonGroup = new JPanel();
        pageEndButtonGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        pageEndButtonGroup.add(saveBtn);
        pageEndButtonGroup.add(cancelBtn);
        saveBtn.addActionListener(e -> {
            String name = recipeNameTextfield.getText();
            if (name.length() <= 0) {
                JOptionPane.showMessageDialog(null, "Please input recipe name!");
                return;
            }
            try {
                m.setRecipeIngredients(recipeIngredientEntryList.getIngredientList());
                m.getRecipe().setIngredients(m.getRecipeIngredients());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid Ingredient Amount");
                return;
            } catch (DuplicateObjectException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Some ingredients in recipe is duplicated.");
                return;
            }
            try {
                m.setBatchSize(Integer.parseInt(recipeBatchSizeTextfield.getText()));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Wrong batch size format.");
                return;
            }
            m.getRecipe().setName(name);
            m.getRecipe().setDescription(recipeDescriptionTextArea.getText());

            c.saveRecipe(Integer.parseInt(recipeBatchSizeTextfield.getText()));
            dispose();
        });

        cancelBtn.addActionListener(e -> {
            c.cancel();
            dispose();
        });
        this.add(pageEndButtonGroup, BorderLayout.PAGE_END);
    }

    @Override
    public void update() {

    }
}
