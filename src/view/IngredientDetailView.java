package view;

import javax.swing.*;
import java.awt.*;

public class IngredientDetailView extends View {

    public IngredientDetailView(){
        this.setTitle("Brew Day! - Recipe Detail"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        JButton btn1 = new JButton("< Back");
        jp1.add(btn1);
        JButton btn2 = new JButton("Edit");
        jp1.add(btn2);
        this.add(jp1, BorderLayout.PAGE_START);
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
