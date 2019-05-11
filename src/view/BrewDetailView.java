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

public class BrewDetailView extends View {
    private BrewDetailController c;
    private Recipe recipe;
    private JTable table;
    private DefaultTableModel tableModel;
    private double originBatchSize;
    private double currentBatchSize;

    public BrewDetailView(BrewDetailController c, Recipe recipe){
        this.c = c;
        this.recipe = recipe;
        this.setTitle("Brew Day! - Brew Recipe Details"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        this.table = new JTable();
        this.originBatchSize = 1000;
        this.currentBatchSize = 1000;

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
            currentBatchSize = Double.parseDouble(batchSizeTextField.getText());
            c.applyBatchSize(originBatchSize, currentBatchSize);
            try {
                double equimentBatchSize = Equipment.getEquipment(1).getVolume();
                if(currentBatchSize>0&&currentBatchSize< equimentBatchSize){
                    originBatchSize = currentBatchSize;
                }
            } catch (FetchDataException| InvalidInputException |EmptyNameException ex) {
                ex.printStackTrace();
            }
        });

        pageTitle.add(textfieldWithLabel, BorderLayout.LINE_END);
        mainPanel.add(pageTitle, BorderLayout.PAGE_START);
        String[] columnNames = {"Ingredient", "Unit", "Amount"};

        Object[][] data = initObjectTable();

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton brewButton = new JButton("Brew");
        bottomLeftButtonBar.add(brewButton);
        brewButton.addActionListener(e -> {
            c.brewRecipe(Integer.parseInt(batchSizeTextField.getText()));
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
