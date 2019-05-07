package view;

import model.StorageIngredient;
import utils.FetchDataException;
import controller.IngredientController;
import controller.IngredientListController;
import model.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IngredientListView extends View {
    private IngredientListController c;
    private IngredientController ic;
    private ArrayList<StorageIngredient> m;
    private JPanel mainPanel;

    public IngredientListView(IngredientListController c, ArrayList<StorageIngredient> m){
        this.c = c;
        this.ic = new IngredientController();
        this.setTitle("Brew Day! - Ingredients List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());
        this.m = m;
        this.mainPanel = new JPanel();

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
        JPanel mainBody = new JPanel();
        mainBody.setLayout(new BorderLayout());

        JPanel JPanelSearchBar = new JPanel();
        JPanelSearchBar.setLayout(new FlowLayout());
        JTextField recipe_name_textfield = new JTextField();
        recipe_name_textfield.setColumns(20);
        JPanelSearchBar.add(recipe_name_textfield);
        JButton buttonSearch = new JButton("Search");
        JButton buttonAdd = new JButton("Add");
        JPanelSearchBar.add(buttonSearch);
        JPanelSearchBar.add(buttonAdd);
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search Clicked");
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.addIngredient();
                dispose();
            }
        });
        mainBody.add(JPanelSearchBar, BorderLayout.PAGE_START);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        createIngredientList(m);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(mainPanel);
        mainBody.add(scrollPane);
        //mainBody.add(mainPanel, BorderLayout.CENTER);
        // TODO: Ingredient List

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

            detailBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.ingredientDetail(ingredient);
                    dispose();
                }
            });
            editBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.editIngredient(ingredient);
                    dispose();
                }
            });
            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int isDelete = JOptionPane.showConfirmDialog(null,
                            String.format("You are going to delete ingredient %s", ingredient.getName()),
                            "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (isDelete == JOptionPane.YES_OPTION) {
                        boolean status = ingredient.delete();
                        if (!status) {
                            JOptionPane.showMessageDialog(null,
                                    String.format("Delete %s failed.", ingredient.getName()));
                        }
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
        try {
            createIngredientList(StorageIngredient.getAll());
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
        mainPanel.revalidate();
    }
}
