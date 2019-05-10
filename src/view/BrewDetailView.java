package view;

import controller.BrewDetailController;
import model.Recipe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BrewDetailView extends View {
    private BrewDetailController c;
    private Recipe recipe;
    private JTable table;
    private DefaultTableModel tableModel;
    public BrewDetailView(BrewDetailController c, Recipe recipe){
        this.c = c;
        this.recipe = recipe;
        this.setTitle("Brew Day! - Brew Recipe Details"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        this.table = new JTable();

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        button.addActionListener(e -> {
            c.goBack();
            dispose();
        });
        this.add(topLeftButtonBar, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel pageTitle = new JPanel();
        pageTitle.setLayout(new BorderLayout());
        JLabel title = new JLabel(this.recipe.getName());
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        pageTitle.add(title, BorderLayout.LINE_START);

        JPanel textfieldWithLabel = new JPanel();
        textfieldWithLabel.setLayout(new FlowLayout(FlowLayout.CENTER));

        textfieldWithLabel.add(new JLabel("Batch Size"));
        JTextField batchSizeTextField = new JTextField();
        batchSizeTextField.setColumns(5);
        batchSizeTextField.setText("1000");
        batchSizeTextField.setToolTipText("Batch Size");
        textfieldWithLabel.add(batchSizeTextField);
        JButton applyBatchSize = new JButton("Apply");
        textfieldWithLabel.add(applyBatchSize);
        applyBatchSize.addActionListener(e -> {
            c.applyBatchSize(Double.valueOf(batchSizeTextField.getText()));
            System.out.println(recipe.getIngredients().get(0).getAmount());
        });
//  TODO: Listen to the change of batch size and update

        pageTitle.add(textfieldWithLabel, BorderLayout.LINE_END);
        mainPanel.add(pageTitle, BorderLayout.PAGE_START);
        String[] columnNames = {"Ingredient", "Unit", "Amount"};

        Object[][] data = new Object[recipe.getIngredients().size()][3];
        for (int i = 0;i<recipe.getIngredients().size();i++) {
            data[i][0] = recipe.getIngredients().get(i).getName();
            data[i][1] = recipe.getIngredients().get(i).getUnit();
            data[i][2] = recipe.getIngredients().get(i).getAmount();
        }

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);

        mainPanel.add(table, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton brewButton = new JButton("Brew");
        bottomLeftButtonBar.add(brewButton);
        brewButton.addActionListener(e -> {
            c.brewRecipe(Double.valueOf(batchSizeTextField.getText()));
            dispose();
        });
        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }


    @Override
    public void update() {
        String[] columnNames = {"Ingredient", "Unit", "Amount"};

        Object[][] data = new Object[recipe.getIngredients().size()][3];
        for (int i = 0;i<recipe.getIngredients().size();i++) {
            data[i][0] = recipe.getIngredients().get(i).getName();
            data[i][1] = recipe.getIngredients().get(i).getUnit();
            data[i][2] = recipe.getIngredients().get(i).getAmount();
        }
        tableModel.setDataVector(data, columnNames);
        tableModel.fireTableDataChanged();
    }
}
