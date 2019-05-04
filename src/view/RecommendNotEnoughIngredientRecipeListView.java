package view;

import controller.RecommendNotEnoughIngredientRecipeListController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecommendNotEnoughIngredientRecipeListView extends View{
    private RecommendNotEnoughIngredientRecipeListController c;
    public RecommendNotEnoughIngredientRecipeListView(RecommendNotEnoughIngredientRecipeListController c){
        this.c = c;
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
        JPanel missingLabel = new JPanel();
        missingLabel.setBackground(Color.YELLOW);
        JLabel missingAlert = new JLabel("Ingredient Not Enough");
        missingLabel.add(missingAlert);
        topLeftButtonBar.add(missingLabel);


        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
            }
        });

        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BoxLayout(jp_main,BoxLayout.Y_AXIS));
        ButtonGroup bg = new ButtonGroup();
        for(int i =0;i<5;i++) {
            JRadioButton msg_ingreNamei = new JRadioButton("Recipe E" + "11" + "Ingredient in use");
            bg.add(msg_ingreNamei);
            jp_main.add(msg_ingreNamei);
        }
        this.add(jp_main, BorderLayout.CENTER);

//        JPanel jp_foot = new JPanel();
//        jp_foot.setLayout(new FlowLayout(FlowLayout.RIGHT));
//
//        JButton btn_generate = new JButton("Generate Shopping List");
//        btn_generate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                c.generateShoppingList();
//            }
//        });
//        jp_foot.add(btn_generate);
//
//        this.add(jp_foot, BorderLayout.SOUTH);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton generateListBtn = new JButton("Generate Shopping List");
        bottomLeftButtonBar.add(generateListBtn);

        generateListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.generateShoppingList();
            }
        });

        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }
    @Override
    public void update() {
        //repaint();
    }
}
