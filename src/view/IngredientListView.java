package view;

import javax.swing.*;
import java.awt.*;

public class IngredientListView extends View {

    public IngredientListView(){
        this.setTitle("Brew Day! - Ingredients List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

//        back button
        JButton buttonBack = new JButton("< back");
        jp_header.add(buttonBack);
        this.add(jp_header, BorderLayout.PAGE_START);

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
