package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrewDetailView extends View {
    public BrewDetailView(){
        this.setTitle("Brew Day! - Brew Recipe Details"); // set frame title
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
        word.setLayout(new BorderLayout());
        JLabel title = new JLabel("Recipe C"); // Recipe Name
        //greeting.setHorizontalAlignment(JLabel.CENTER); // Vertical central the label in BorderLayout
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));
        word.add(title, BorderLayout.LINE_START);
        JTextField batchSize = new JTextField();
        batchSize.setColumns(5);
        batchSize.setText("1000");
        batchSize.setToolTipText("Batch Size");
        word.add(batchSize, BorderLayout.LINE_END);
        jp2.add(word, BorderLayout.PAGE_START);
        String[] columnNames = {"Ingredient", "Unit", "Amount"};

        Object[][] data =
                {
                        {"Barley", "GRAM", "3.5"},
                        {"Yeast", "MILLILITER", "25"},
                };

        JTable table = new JTable(data, columnNames);
        jp2.add(table, BorderLayout.CENTER);
        this.add(jp2, BorderLayout.CENTER);
        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btn_save = new JButton("Brew");
        jp_foot.add(btn_save);
        this.add(jp_foot, BorderLayout.PAGE_END);
    }
    @Override
    public void update() {

    }
}
