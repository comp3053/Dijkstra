package view;

import controller.RecipeFormController;
import utils.EmptyNameException;
import model.RecipeIngredient;
import model.StorageIngredient;
import utils.InvalidInputException;
import utils.UnitEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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


        ArrayList<StorageIngredient> testIngredient = new ArrayList<>();
        try{
            testIngredient.add(new StorageIngredient(1,"A1", 2.0, UnitEnum.GRAM));
            testIngredient.add(new StorageIngredient(2,"A2", 3.0, UnitEnum.LITER));
            testIngredient.add(new StorageIngredient(3,"A3", 2.0, UnitEnum.KILOGRAM));
            testIngredient.add(new StorageIngredient(4,"A4", 20.0, UnitEnum.MILLILITER));
            testIngredient.add(new StorageIngredient(5,"A5", 0.2, UnitEnum.KILOGRAM));
            testIngredient.add(new StorageIngredient(6,"A6", 2.0, UnitEnum.GRAM));
        }catch (EmptyNameException | InvalidInputException e){
            e.printStackTrace();
        }

        RecipeIngredientEntryList recipeIngredientEntryList = new RecipeIngredientEntryList(testIngredient);
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
        JTextArea recipeDescrptionTextArea = new JTextArea(5,30);
        recipeDescription.add(recipeDescrptionTextArea);
        buttonRecipeForm.add(recipeDescription, BorderLayout.PAGE_END);

        mainPanel.add(buttonRecipeForm, BorderLayout.PAGE_END);
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
                ArrayList<RecipeIngredient> recipeIngredients = recipeIngredientEntryList.getIngredientList();
//                GET the Ingredient List from GUI
//                Check if there is a duplicate ingredient
//                Check if there are invalid value
//                Check if the fields are valid.
                c.saveRecipe();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.cancel();
                dispose();
            }
        });
        this.add(pageEndButtonGroup, BorderLayout.PAGE_END);
    }

    @Override
    public void update(){

    }
}
