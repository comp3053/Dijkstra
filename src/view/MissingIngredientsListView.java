package view;

import controller.MissingIngredientListController;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MissingIngredientsListView extends View{
    private MissingIngredientListController c;
    private Recipe recipe;
    public MissingIngredientsListView(MissingIngredientListController c, Recipe recipe){
        this.c = c;
        this.recipe = recipe;
        this.setTitle("Brew Day! - Missing Ingredient List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btn_back = new JButton("< Back");
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
                dispose();
            }
        });
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Missing Ingredient List For Recipe" + this.recipe.getName());
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.NORTH);

        c.readMissingIngredientList();

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BoxLayout(jp_main,BoxLayout.Y_AXIS));
        JPanel jp_main_sub = new JPanel();
        jp_main_sub.setLayout(new FlowLayout());
        JLabel msg_headName = new JLabel("name");
        jp_main_sub.add(msg_headName);
        JLabel msg_headUnit = new JLabel("unit");
        jp_main_sub.add(msg_headUnit);
        JLabel msg_headAmount = new JLabel("amount");
        jp_main_sub.add(msg_headAmount);
        jp_main.add(jp_main_sub);
        for(int i =0;i<this.recipe.getIngredients().size();i++) {
            jp_main_sub = new JPanel();
            JLabel msg_ingreNamei = new JLabel(this.recipe.getIngredients().get(i).getName());
            jp_main_sub.add(msg_ingreNamei);
            JLabel msg_ingreUniti = new JLabel(this.recipe.getIngredients().get(i).getUnit().name());
            jp_main_sub.add(msg_ingreUniti);
            JLabel msg_ingreAmounti = new JLabel(""+this.recipe.getIngredients().get(i).getAmount());
            jp_main_sub.add(msg_ingreAmounti);
            jp_main.add(jp_main_sub);
        }
        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btn_OK = new JButton("OK");
        btn_OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.OK();
                dispose();
            }
        });
        jp_foot.add(btn_OK);

        this.add(jp_foot, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        //repaint();
    }
}
