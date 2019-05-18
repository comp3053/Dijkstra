package view;

import model.StorageIngredient;
import utils.FetchDataException;
import controller.IngredientListController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IngredientListView extends View {
    private IngredientListController c;
    private ArrayList<StorageIngredient> m;
    private JPanel mainPanel;

    public IngredientListView(IngredientListController c, ArrayList<StorageIngredient> m) {
        this.c = c;
        this.setTitle("Brew Day! - Ingredients List"); // Set frame title
        this.setSize(800, 600); // Set frame size
        this.setLayout(new BorderLayout());
        this.m = m;
        this.mainPanel = new JPanel();

        JPanel mainBody = new JPanel();
        mainBody.setLayout(new BorderLayout());
        JPanel topButtonsAround = new JPanel();
        topButtonsAround.setLayout(new BoxLayout(topButtonsAround, BoxLayout.LINE_AXIS));

        JButton leftButton = new JButton("< Back");
        JButton rightButton = new JButton("Add");

        topButtonsAround.add(leftButton);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(rightButton);

        // Setup action for back button.
        leftButton.addActionListener(e -> {
            c.goBack();
            dispose();
        });

        // Setup action for add ingredient button
        rightButton.addActionListener(e -> {
            c.addIngredient();
            dispose();
        });
        this.add(topButtonsAround, BorderLayout.PAGE_START);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        createIngredientList(m);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(mainPanel);
        mainBody.add(scrollPane);

        this.add(mainBody, BorderLayout.CENTER);


    }


    private void createIngredientList(ArrayList<StorageIngredient> ingredients) {
        for (StorageIngredient ingredient : ingredients) {
            JPanel mainPanelIter = new JPanel();
            mainPanelIter.setLayout(new FlowLayout());
            JLabel ingredientName = new JLabel(ingredient.getName());
            mainPanelIter.add(ingredientName);
            JLabel ingredientAmount = new JLabel("- Storage Amount: " + ingredient.getAmount() + " " + ingredient.getUnit().toString().toLowerCase());
            mainPanelIter.add(ingredientAmount);
            JButton detailBtn = new JButton("detail");
            JButton editBtn = new JButton("edit");
            JButton deleteBtn = new JButton("delete");
            ingredient.addListener(this);

            detailBtn.addActionListener(e -> {
                c.ingredientDetail(ingredient);
                dispose();
            });
            editBtn.addActionListener(e -> {
                c.editIngredient(ingredient);
                dispose();
            });
            deleteBtn.addActionListener(e -> {
                int isDelete = JOptionPane.showConfirmDialog(null,
                        String.format("You are going to delete ingredient %s", ingredient.getName()),
                        "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (isDelete == JOptionPane.YES_OPTION) {
                    boolean status = ingredient.delete();
                    if (!status) {
                        JOptionPane.showMessageDialog(null,
                                String.format("Delete %s failed. There are recipes including this ingredient.", ingredient.getName()));
                    }
                }
            });
            mainPanelIter.add(detailBtn);
            mainPanelIter.add(editBtn);
            mainPanelIter.add(deleteBtn);
            mainPanel.add(mainPanelIter);
        }
    }


    @Override
    public void update() {
        mainPanel.removeAll();
        mainPanel.repaint();
        try {
            createIngredientList(StorageIngredient.getAll());
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
        mainPanel.revalidate();
    }
}
