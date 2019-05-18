package view;

import controller.EquipmentInfoController;
import model.Equipment;

import javax.swing.*;
import java.awt.*;

public class EquipmentInfoView extends View {
    private EquipmentInfoController c;
    private Equipment m;
    private JLabel nameValue;
    private JLabel volumeValue;

    /**
     * User interface of equipment detail.
     * @param c Controller for equipment detail.
     * @param m Information of your equipment.
     */
    public EquipmentInfoView(EquipmentInfoController c, Equipment m) {
        this.c = c;
        this.m = m;
        this.setTitle("Brew Day! - Equipment Information"); // set frame title
        this.setSize(600, 400); // set frame size
        this.setLayout(new BorderLayout());

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Equipment Information");
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        button.addActionListener(e -> {
            c.goBack();
            dispose();
        });
        this.add(topLeftButtonBar, BorderLayout.PAGE_START);


        JPanel mainPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(groupLayout);// Vertically display
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Name");
        JLabel volumeLabel = new JLabel("Volume (unit: mL)");

        this.nameValue = new JLabel(m.getName());
        this.volumeValue = new JLabel(Integer.toString(m.getVolume()));

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();

        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameLabel)
                .addComponent(volumeLabel));
        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameValue)
                .addComponent(volumeValue));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();

        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel).addComponent(nameValue));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(volumeLabel).addComponent(volumeValue));

        groupLayout.setVerticalGroup(vGroup);

        this.add(mainPanel, BorderLayout.CENTER);


        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Update");
        bottomLeftButtonBar.add(saveButton);

        // Setup action for save button
        saveButton.addActionListener(e -> {
            c.turnUpdateInfo();
            dispose();
        });
        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }

    @Override
    public void update() {
        this.nameValue = new JLabel(m.getName());
        this.volumeValue = new JLabel(Integer.toString(m.getVolume()));
    }
}