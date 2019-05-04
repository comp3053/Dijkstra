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
        mainBody.add(JPanelSearchBar, BorderLayout.PAGE_START);

        String[] columnNames = {"Ingredient", "Storage Amount", "Operation"};

        Object[][] data =
                {
                        {"Barley", "1.5g", "detail edit delete"},
                        {"Yeast", "1.5g", "detail edit delete"},
                };

        JTable table = new JTable(data, columnNames);
        mainBody.add(table, BorderLayout.CENTER);
        // TODO: Ingredient List

        this.add(mainBody, BorderLayout.CENTER);

        
    }


    @Override
    public void update() {

    }
}
