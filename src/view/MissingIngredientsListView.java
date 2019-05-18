package view;

import controller.MissingIngredientListController;
import model.Recipe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MissingIngredientsListView extends View {
    private MissingIngredientListController c;
    private Recipe recipe;
    private JTable table;
    private DefaultTableModel tableModel;

    public MissingIngredientsListView(MissingIngredientListController c, Recipe recipe) {
        this.c = c;
        this.recipe = recipe;
        this.setTitle("Brew Day! - Missing Ingredient List"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btn_back = new JButton("< Back");
        btn_back.addActionListener(e -> {
            c.goBack();
            dispose();
        });
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Missing Ingredient List For Recipe " + this.recipe.getName());
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.NORTH);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        // Automatically display the corresponding required ingredient(name, amount, unit) in table.
        String[] columnNames = {"Ingredient", "Amount", "Unit"};

        Object[][] data = new Object[recipe.getIngredients().size()][3];
        recipe.getIngredients().forEach(ingredient -> {
            int index = recipe.getIngredients().indexOf(ingredient);
            data[index][0] = ingredient.getName();
            data[index][1] = ingredient.getAmount() > 0 ? ingredient.getAmount() : "Storage Sufficiently";
            data[index][2] = ingredient.getUnit();
        });

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btn_OK = new JButton("OK");
        btn_OK.addActionListener(e -> {
            c.OK();
            dispose();
        });
        jp_foot.add(btn_OK);

        this.add(jp_foot, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        String[] columnNames = {"Ingredient", "Unit", "Amount"};
        Object[][] data = new Object[recipe.getIngredients().size()][3];
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            data[i][0] = recipe.getIngredients().get(i).getName();
            data[i][1] = recipe.getIngredients().get(i).getUnit();
            data[i][2] = recipe.getIngredients().get(i).getAmount();
        }
        tableModel.setDataVector(data, columnNames);
        tableModel.fireTableDataChanged();
    }
}
