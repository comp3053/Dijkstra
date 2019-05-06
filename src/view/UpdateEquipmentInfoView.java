package view;

import controller.UpdateEquipmentInfoController;
import model.EmptyEquipmentNameException;
import model.Equipment;
import model.InvalidEquipmentVolumeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEquipmentInfoView extends View{
    private UpdateEquipmentInfoController c;
    private Equipment m;
    public UpdateEquipmentInfoView(UpdateEquipmentInfoController c, Equipment m){
        this.c = c;
        this.m = m;
        this.setTitle("Brew Day! - Equipment Update"); // set frame title
        this.setSize(600, 400); // set frame size
        this.setLayout(new BorderLayout());


        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Update Equipment Information");
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
                dispose();
            }
        });
        this.add(topLeftButtonBar, BorderLayout.PAGE_START);


        JTextField name = new JTextField();
        name.setColumns(10);
        name.setToolTipText("Name");
        JTextField volume = new JTextField();
        volume.setColumns(10);
        volume.setToolTipText("Volume");

        JPanel mainPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(groupLayout);// Vertically display
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Name");
        JLabel volumeLabel = new JLabel("Volume (unit: L)");

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();

        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameLabel)
                .addComponent(volumeLabel));
        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(name)
                .addComponent(volume));
//        mainPanel.setBorder(new EmptyBorder(0,0,350,0));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();

        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel).addComponent(name));
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(volumeLabel).addComponent(volume));

        groupLayout.setVerticalGroup(vGroup);

        //TODO: can replace these column with last equipment information

        this.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomLeftButtonBar = new JPanel();
        bottomLeftButtonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        bottomLeftButtonBar.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(c.saveEquipmentInfo(name.getText(),volume.getText())) {
                        JOptionPane.showMessageDialog(null, "Equipment Information have been saved");
                    }
                    c.goBack();
                    dispose();
                } catch (EmptyEquipmentNameException | InvalidEquipmentVolumeException | NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Input invalid.");
                }
            }
        });
        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }

    @Override
    public void update() {
        //repaint();
    }
}
