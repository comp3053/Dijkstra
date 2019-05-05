package view;

import controller.IngredientListController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientListView extends View {
    private IngredientListController c;
    public IngredientListView(IngredientListController c){
        this.c = c;
        this.setTitle("Brew Day! - Ingredients List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

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
        for (int i = 0; i < 5; i++) {
            JPanel mainPanelIter = new JPanel();
            mainPanelIter.setLayout(new FlowLayout());
            JLabel ingredientName = new JLabel("Barley");
            mainPanelIter.add(ingredientName);
            JLabel ingredientAmount = new JLabel("15g");
            mainPanelIter.add(ingredientAmount);
            JButton detailBtn = new JButton("detail");
            JButton editBtn = new JButton("edit");
            JButton deleteBtn = new JButton("delete");
            detailBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.ingredientDetail();
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
