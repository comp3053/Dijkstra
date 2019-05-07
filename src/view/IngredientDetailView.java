package view;

import controller.IngredientController;
import controller.IngredientDetailController;
import model.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientDetailView extends View {
    private IngredientDetailController c;
    private IngredientController ic;
    private Ingredient ingredient;
    public IngredientDetailView(IngredientDetailController c,Ingredient ingredient){
        this.c = c;
        this.ic = new IngredientController();
        this.setTitle("Brew Day! - Recipe Detail"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        this.ingredient=ingredient;

        JPanel topButtonsAround = new JPanel();
        topButtonsAround.setLayout(new BoxLayout(topButtonsAround, BoxLayout.LINE_AXIS));

        JButton backBtn = new JButton("< Back");
        JButton editBtn = new JButton("Edit");

        topButtonsAround.add(backBtn);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(editBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
                dispose();
            }
        });

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
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
