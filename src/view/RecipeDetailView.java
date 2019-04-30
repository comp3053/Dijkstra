package view;


import javax.swing.*;
import java.awt.*;

public class RecipeDetailView extends View {

    public RecipeDetailView(){
        this.setTitle("Brew Day! - Recipe Detail"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        JButton btn1 = new JButton("< Back");
        jp1.add(btn1);
        this.add(jp1, BorderLayout.PAGE_START);
        JPanel jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        JPanel word = new JPanel();
        word.setLayout(new BoxLayout(word, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Recipe detail");
        //greeting.setHorizontalAlignment(JLabel.CENTER); // Vertical central the label in BorderLayout
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        JLabel subtitle = new JLabel("Brew Recipe 1"); // TODO: Name of the recipe will shown here
        subtitle.setFont(new Font(subtitle.getFont().getFontName(), subtitle.getFont().getStyle(), 16));
        word.add(title);
        word.add(subtitle);
        jp2.add(word, BorderLayout.PAGE_START);
        JPanel jp3 = new JPanel();
        jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
        JLabel ingredient1 = new JLabel("A Malts 1.0 gram");
        JLabel ingredient2 = new JLabel("B Yeasts 2.0 grams");
        JLabel ingredient3 = new JLabel("Water 1000.0 Milliliters");
        jp3.add(ingredient1);
        jp3.add(ingredient2);
        jp3.add(ingredient3);
        jp2.add(jp3, BorderLayout.CENTER);
        this.add(jp2, BorderLayout.CENTER);
    }

    @Override
    public void update(){

    }
}
