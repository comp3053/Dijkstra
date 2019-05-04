package view;

import controller.EquipmentController;
import controller.EquipmentInfoController;
import controller.FetchDataException;
import model.EmptyEquipmentNameException;
import model.Equipment;
import model.InvalidEquipmentVolumeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipmentInfoView extends View{
    private EquipmentInfoController c;
    private EquipmentController ec;
    private Equipment equipment;

    public EquipmentInfoView(EquipmentInfoController c){
        this.c = c;
        this.ec = new EquipmentController();
        this.setTitle("Brew Day! - Equipment Information"); // set frame title
        this.setSize(600, 400); // set frame size
        this.setLayout(new BorderLayout());
        try {
            this.equipment = ec.getLatestEquipment();//automatically get latest version of equipment
        } catch (FetchDataException | InvalidEquipmentVolumeException | EmptyEquipmentNameException e) {
            e.printStackTrace();
        }

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Equipment Information");
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


        JPanel mainPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(groupLayout);// Vertically display
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel();
        JLabel volumeLabel = new JLabel("Volume (unit: L)");

        JLabel nameValue = new JLabel(this.equipment.getName());
        JLabel volumeValue = new JLabel(""+this.equipment.getVolume());

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();

        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameLabel)
                .addComponent(volumeLabel));
        hGroup.addGroup(groupLayout.createParallelGroup().addComponent(nameValue)
                .addComponent(volumeValue));
//        mainPanel.setBorder(new EmptyBorder(0,0,350,0));
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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.turnUpdateInfo();
            }// TODO: I think here can be removed because update function can be implemented
        });
        this.add(bottomLeftButtonBar, BorderLayout.PAGE_END);
    }

    @Override
    public void update() {
        //repaint();
    }
}