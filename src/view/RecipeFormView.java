package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeFormView extends View {
    public RecipeFormView(){
        this.setTitle("Brew Day! - Recipe Form"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame

        JPanel page_start_title = new JPanel();
        page_start_title.setLayout(new BoxLayout(page_start_title, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Recipe Form");
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        page_start_title.add(title);
        this.add(page_start_title, BorderLayout.PAGE_START);

        JPanel form_main_body = new JPanel();
        form_main_body.setLayout(new BoxLayout(form_main_body, BoxLayout.Y_AXIS));

        JPanel recipe_name_field = new JPanel();
        recipe_name_field.setLayout(new FlowLayout());
        JLabel recipe_name_title = new JLabel("Recipe Name:");
//        recipe_name_title.setFont(new Font(recipe_name_title.getFont().getFontName(), recipe_name_title.getFont().getStyle(), 16));
        recipe_name_field.add(recipe_name_title);
        JTextField recipe_name_textfield = new JTextField();
        recipe_name_textfield.setColumns(20);
        recipe_name_field.add(recipe_name_textfield);
        form_main_body.add(recipe_name_field);
        this.add(form_main_body);

        // TODO: Ingredients Field

        JPanel recipe_batch_size = new JPanel();
        recipe_batch_size.setLayout(new FlowLayout());
        JLabel recipe_batch_size_title = new JLabel("Batch Size:");
//        recipe_name_title.setFont(new Font(recipe_name_title.getFont().getFontName(), recipe_name_title.getFont().getStyle(), 16));
        recipe_batch_size.add(recipe_batch_size_title);
        JTextField recipe_batch_size_textfield = new JTextField();
        recipe_batch_size_textfield.setColumns(20);
        recipe_batch_size.add(recipe_batch_size_textfield);
        form_main_body.add(recipe_batch_size);
        this.add(form_main_body);

        JPanel page_end_button_group = new JPanel();
        page_end_button_group.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton btn1 = new JButton("Save");
        JButton btn2 = new JButton("Canel");
        page_end_button_group.add(btn1);
        page_end_button_group.add(btn2);
        this.add(page_end_button_group, BorderLayout.PAGE_END);

    }

    @Override
    public void update(){

    }
}
