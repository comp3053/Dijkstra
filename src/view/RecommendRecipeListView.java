package view;

import controller.RecommendRecipeListController;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecommendRecipeListView extends View{
    private RecommendRecipeListController c;
    private ArrayList<Recipe> recommendRecipe;

    public RecommendRecipeListView(RecommendRecipeListController c,ArrayList<Recipe> recommendRecipe){
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

        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
                dispose();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        ButtonGroup bg = new ButtonGroup();
        for(int i =0;i<recommendRecipe.size();i++) {
            JRadioButton msg_ingreNamei = new JRadioButton(recommendRecipe.get(i).getName() + ": 11"+ "Ingredient in used");//TODO:show the number of recipeIngredient
            bg.add(msg_ingreNamei);
            mainPanel.add(msg_ingreNamei);
        }
        // TODO: Need to change to scrollable
        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton brewButton = new JButton("Brew this recipe");
        bottomLeftButtonBar.add(brewButton);

        brewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.brewRecipe();
                dispose();
            }
        });

        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }
    @Override
    public void update() {
        //repaint();
    }
}
