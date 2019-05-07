package view;

import controller.BrewDetailController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrewDetailView extends View {
    private BrewDetailController c;
    public BrewDetailView(BrewDetailController c){
        this.c = c;
        this.setTitle("Brew Day! - Brew Recipe Details"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame

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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel pageTitle = new JPanel();
        pageTitle.setLayout(new BorderLayout());
        JLabel title = new JLabel("Recipe C"); // Recipe Name
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        pageTitle.add(title, BorderLayout.LINE_START);

        JPanel textfieldWithLabel = new JPanel();
        textfieldWithLabel.setLayout(new FlowLayout(FlowLayout.CENTER));

        textfieldWithLabel.add(new JLabel("Batch Size"));
        JTextField batchSize = new JTextField();
        batchSize.setColumns(5);
        batchSize.setText("1000");
        batchSize.setToolTipText("Batch Size");
        textfieldWithLabel.add(batchSize);
        JButton applyBatchSize = new JButton("Apply");
        textfieldWithLabel.add(applyBatchSize);
        applyBatchSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.applyBatchSize(Integer.parseInt(batchSize.getText()));
                //TODO
            }
        });
//  TODO: Listen to the change of batch size and update

        pageTitle.add(textfieldWithLabel, BorderLayout.LINE_END);
        mainPanel.add(pageTitle, BorderLayout.PAGE_START);
        String[] columnNames = {"Ingredient", "Unit", "Amount"};

        Object[][] data =
                {
                        {"Barley", "GRAM", "3.5"},
                        {"Yeast", "MILLILITER", "25"},
                };

        JTable table = new JTable(data, columnNames);
        mainPanel.add(table, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton brewButton = new JButton("Brew");
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

    }
}
