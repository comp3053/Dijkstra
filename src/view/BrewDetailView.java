package view;

import controller.BrewDetailController;
import model.Equipment;
import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BrewDetailView extends View {
    private BrewDetailController c;
    private Recipe recipe;
    private DefaultTableModel tableModel;
    private int originBatchSize;
    private int currentBatchSize;
    private int equipmentBatchSize;

    public BrewDetailView(BrewDetailController c, Recipe recipe, ArrayList<Recipe> recommendRecipes) {
        this.c = c;
        this.recipe = recipe;
        this.setTitle("Brew Day! - Brew Recipe Details"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout()); // Set BorderLayout to the frame
        try {
            this.equipmentBatchSize = Equipment.getEquipment(1).getVolume();
            this.originBatchSize = equipmentBatchSize;
            this.currentBatchSize = equipmentBatchSize;
        } catch (FetchDataException | InvalidInputException | EmptyNameException e) {
            e.printStackTrace();
        }

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        button.addActionListener(e -> {
            c.goBack(recommendRecipes);
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

        textfieldWithLabel.add(new JLabel("Batch Size (mL)"));
        JTextField batchSizeTextField = new JTextField();
        batchSizeTextField.setColumns(5);
        batchSizeTextField.setText(String.valueOf(this.equipmentBatchSize));
        batchSizeTextField.setToolTipText("Batch Size");
        textfieldWithLabel.add(batchSizeTextField);
        JButton applyBatchSize = new JButton("Apply");
        textfieldWithLabel.add(applyBatchSize);
        applyBatchSize.addActionListener(e -> {
            try {
                currentBatchSize = Integer.parseInt(batchSizeTextField.getText());
                System.out.println(currentBatchSize + "  " + originBatchSize);
                if (currentBatchSize > 0 && currentBatchSize <= equipmentBatchSize) {
                    c.applyBatchSize(originBatchSize, currentBatchSize);
                    originBatchSize = currentBatchSize;
                } else {
                    JOptionPane.showMessageDialog(null, "Batch size should greater than 0 and less or equal to you equipment volume " + equipmentBatchSize);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Batch size should be a positive integer!");

            }
        });

        pageTitle.add(textfieldWithLabel, BorderLayout.LINE_END);
        mainPanel.add(pageTitle, BorderLayout.PAGE_START);
        String[] columnNames = {"Ingredient", "Unit", "Amount"};

        Object[][] data = initObjectTable();

        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton brewButton = new JButton("Brew");
        bottomLeftButtonBar.add(brewButton);
        brewButton.addActionListener(e -> {
            try {
                c.brewRecipe(currentBatchSize);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid batch size!");
                return;
            }
            dispose();
        });
        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }

    private Object[][] initObjectTable() {
        Object[][] data = new Object[recipe.getIngredients().size()][3];
        recipe.getIngredients().forEach(ingredient -> {
            int index = recipe.getIngredients().indexOf(ingredient);
            data[index][0] = ingredient.getName();
            data[index][1] = ingredient.getUnit();
            data[index][2] = ingredient.getAmount();
        });
        return data;
    }


    @Override
    public void update() {
        String[] columnNames = {"Ingredient", "Unit", "Amount"};
        Object[][] data = initObjectTable();
        tableModel.setDataVector(data, columnNames);
        tableModel.fireTableDataChanged();
    }
}
