package view;

import controller.FetchDataException;
import controller.IngredientController;
import controller.IngredientListController;
import model.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IngredientListView extends View {
    private IngredientListController c;
    private IngredientController ic;
    private ArrayList<Ingredient> ingredients;
    public IngredientListView(IngredientListController c){
        this.c = c;
        this.ic = new IngredientController();
        this.setTitle("Brew Day! - Ingredients List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());
        try {
            this.ingredients=ic.getAll();
        } catch (FetchDataException e) {
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

        JPanel mainBody = new JPanel();
        mainBody.setLayout(new BorderLayout());

        JPanel JPanelSearchBar = new JPanel();
        JPanelSearchBar.setLayout(new FlowLayout());
        JTextField recipe_name_textfield = new JTextField();
        recipe_name_textfield.setColumns(20);
        JPanelSearchBar.add(recipe_name_textfield);
        JButton buttonSearch = new JButton("Search");
        JButton buttonAdd = new JButton("Add");
        JPanelSearchBar.add(buttonSearch);
        JPanelSearchBar.add(buttonAdd);
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search Clicked");
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.addIngredient();
                dispose();
            }
        });
        mainBody.add(JPanelSearchBar, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < ingredients.size(); i++) {
            JPanel mainPanelIter = new JPanel();
            mainPanelIter.setLayout(new FlowLayout());
            JLabel ingredientName = new JLabel(ingredients.get(i).getName());
            mainPanelIter.add(ingredientName);
            JLabel ingredientAmount = new JLabel(""+ingredients.get(i).getAmount()+ingredients.get(i).getUnit());
            mainPanelIter.add(ingredientAmount);
            JButton detailBtn = new JButton("detail");
            JButton editBtn = new JButton("edit");
            JButton deleteBtn = new JButton("delete");

            Ingredient ingredient = ingredients.get(i);
            detailBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.ingredientDetail(ingredient);
                    dispose();
                }
            });
            editBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO: Edit ingredient
                }
            });
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO: Delete ingredient
                }
            });
            mainPanelIter.add(detailBtn);
            mainPanelIter.add(editBtn);
            mainPanelIter.add(deleteBtn);
            mainPanel.add(mainPanelIter);
        }
//        String[] columnNames = {"Ingredient", "Storage Amount", "Operation"};
//
//        Object[][] data =
//                {
//                        {"Barley", "1.5g", "detail edit delete"},
//                        {"Yeast", "1.5g", "detail edit delete"},
//                };
//
//        JTable table = new JTable(data, columnNames);

        mainBody.add(mainPanel, BorderLayout.CENTER);
        // TODO: Ingredient List

        this.add(mainBody, BorderLayout.CENTER);

        
    }


    @Override
    public void update() {

    }
}
