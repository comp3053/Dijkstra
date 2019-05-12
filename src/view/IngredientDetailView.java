package view;

import controller.IngredientDetailController;
import model.StorageIngredient;

import javax.swing.*;
import java.awt.*;

public class IngredientDetailView extends View {
    private IngredientDetailController c;
    private StorageIngredient ingredient;

    public IngredientDetailView(IngredientDetailController c, StorageIngredient ingredient) {
        this.c = c;
        this.setTitle("Brew Day! - Recipe Detail"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set BorderLayout to the frame
        this.ingredient = ingredient;

        JPanel topButtonsAround = new JPanel();
        topButtonsAround.setLayout(new BoxLayout(topButtonsAround, BoxLayout.LINE_AXIS));

        JButton backBtn = new JButton("< Back");
        JButton editBtn = new JButton("Edit");

        topButtonsAround.add(backBtn);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(editBtn);

        backBtn.addActionListener(e -> {
            c.goBack();
            dispose();
        });

        editBtn.addActionListener(e -> {
            c.editIngredient(ingredient);
            dispose();
        });
        this.add(topButtonsAround, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(groupLayout);// Vertically display
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Name:");
        JLabel amountLabel = new JLabel("Amount:");
        JLabel unitLabel = new JLabel("Unit:");

        JLabel nameValue = new JLabel(ingredient.getName());
        JLabel amountValue = new JLabel(String.valueOf(ingredient.getAmount()));
        JLabel unitValue = new JLabel(String.valueOf(ingredient.getUnit()));

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();

        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameLabel)
                .addComponent(amountLabel).addComponent(unitLabel));
        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameValue)
                .addComponent(amountValue).addComponent(unitValue));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();

        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel).addComponent(nameValue));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(amountLabel).addComponent(amountValue));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(unitLabel).addComponent(unitValue));

        groupLayout.setVerticalGroup(vGroup);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {

    }
}
