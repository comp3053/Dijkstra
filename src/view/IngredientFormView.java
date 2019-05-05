package view;

import controller.IngredientFormController;
import utils.UnitEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientFormView extends View {
    private IngredientFormController c;

    public IngredientFormView(IngredientFormController c) {
        this.c = c;
        this.setTitle("Brew Day! - Ingredient Form"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel pageTitle = new JPanel();
        pageTitle.setLayout(new BoxLayout(pageTitle, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Ingredient Form");
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        pageTitle.add(title);
        this.add(pageTitle, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(groupLayout);// Vertically display
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Name:");
        JLabel amountLabel = new JLabel("Amount:");
        JLabel unitLabel = new JLabel("Unit:");

        JTextField nameTextField = new JTextField();
        JTextField amountTextField = new JTextField();
        JComboBox<UnitEnum> unitSelect = new JComboBox<>();
        for (UnitEnum unit : UnitEnum.values()) {
            unitSelect.addItem(unit);
        }


        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();

        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameLabel)
                .addComponent(amountLabel)).addComponent(unitLabel);
        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameTextField)
                .addComponent(amountTextField).addComponent(unitSelect));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();

        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel).addComponent(nameTextField));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(amountLabel).addComponent(amountTextField));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(unitLabel).addComponent(unitSelect));

        groupLayout.setVerticalGroup(vGroup);

        //TODO: can replace these column with last equipment information

        this.add(mainPanel, BorderLayout.CENTER);

        JPanel pageEndButtonGroup = new JPanel();
        pageEndButtonGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        pageEndButtonGroup.add(saveBtn);
        pageEndButtonGroup.add(cancelBtn);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: save Ingredient to the DB
                c.saveIngredient(nameTextField.getText(),amountTextField.getText(),unitSelect.getSelectedIndex());
                c.cancel();
                dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.cancel();
                dispose();
            }
        });
        this.add(pageEndButtonGroup, BorderLayout.PAGE_END);
    }

    @Override
    public void update() {

    }
}
