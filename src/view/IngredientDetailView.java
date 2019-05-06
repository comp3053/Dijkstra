package view;

import controller.FetchDataException;
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
        JPanel mainBody = new JPanel();
        mainBody.setLayout(new BoxLayout(mainBody, BoxLayout.Y_AXIS));
        mainBody.add(new JLabel("Name"));
        mainBody.add(new JLabel(ingredient.getName()));
        mainBody.add(new JLabel("Amount"));
        mainBody.add(new JLabel(ingredient.getAmount()+String.valueOf(ingredient.getUnit())));
        this.add(mainBody, BorderLayout.CENTER);
    }

    @Override
    public void update() {

    }
}
