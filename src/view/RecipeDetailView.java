package view;


import utils.FetchDataException;
import controller.RecipeController;
import controller.RecipeDetailController;
import model.EmptyIngredientNameException;
import model.InvalidIngredientAmountException;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeDetailView extends View {
    private RecipeDetailController c;
    private RecipeController rc;
    private Recipe recipe;
    public RecipeDetailView(RecipeDetailController c,int recipeID){
        this.c = c;
        this.rc = new RecipeController();
        this.setTitle("Brew Day! - Recipe Detail"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        try {
            this.recipe = rc.getRecipe(recipeID);
        } catch (FetchDataException | InvalidIngredientAmountException | EmptyIngredientNameException e) {
            e.printStackTrace();
        }

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
                dispose();
            }
        });

        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        JPanel word = new JPanel();
        word.setLayout(new BoxLayout(word, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Recipe detail");
        //greeting.setHorizontalAlignment(JLabel.CENTER); // Vertical central the label in BorderLayout
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        JLabel subtitle = new JLabel(this.recipe.getName());
        subtitle.setFont(new Font(subtitle.getFont().getFontName(), subtitle.getFont().getStyle(), 16));
        word.add(title);
        word.add(subtitle);
        jp2.add(word, BorderLayout.PAGE_START);
        JPanel jp3 = new JPanel();
        jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
        for(int i=0;i<recipe.getIngredients().size();i++) {
            JLabel ingredient = new JLabel(this.recipe.getIngredients().get(i).getName() + ":" + this.recipe.getIngredients().get(i).getAmount() + this.recipe.getIngredients().get(i).getUnit().name());
            jp3.add(ingredient);
        }
        jp2.add(jp3, BorderLayout.CENTER);
        this.add(jp2, BorderLayout.CENTER);
    }

    @Override
    public void update(){

    }
}
