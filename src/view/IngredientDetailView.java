package view;

import controller.IngredientDetailController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientDetailView extends View {
    private IngredientDetailController c;
    public IngredientDetailView(IngredientDetailController c){
        this.c = c;
        this.setTitle("Brew Day! - Recipe Detail"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        JPanel topButtonsAround = new JPanel();
        topButtonsAround.setLayout(new BoxLayout(topButtonsAround, BoxLayout.LINE_AXIS));

        JButton leftButton = new JButton("< Back");
        JButton rightButton = new JButton("Edit");

        topButtonsAround.add(leftButton);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(rightButton);

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        this.add(topButtonsAround, BorderLayout.PAGE_START);
        JPanel mainBody = new JPanel();
        mainBody.setLayout(new BoxLayout(mainBody, BoxLayout.Y_AXIS));
        mainBody.add(new JLabel("Name"));
        mainBody.add(new JLabel("Barley A"));
        mainBody.add(new JLabel("Amount"));
        mainBody.add(new JLabel("1.5 GRAM"));
        this.add(mainBody, BorderLayout.CENTER);
    }

    @Override
    public void update() {

    }
}
