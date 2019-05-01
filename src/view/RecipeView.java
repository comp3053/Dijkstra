package view;

import org.omg.CORBA.BAD_INV_ORDER;
import utils.ButtonColumn;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeView extends View{

    public RecipeView(){
        this.setTitle("Brew Day! - Manage Recipe"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout()); // set borderlayout to the frame
        JPanel topButtonsAround = new JPanel();
        topButtonsAround.setLayout(new BoxLayout(topButtonsAround, BoxLayout.LINE_AXIS));

        JButton leftButton = new JButton("< Back");
        JButton rightButton = new JButton("New");

        topButtonsAround.add(leftButton);
        topButtonsAround.add(Box.createHorizontalGlue());
        topButtonsAround.add(rightButton);

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back clicked");
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new clicked");
            }
        });
        this.add(topButtonsAround, BorderLayout.PAGE_START);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        JPanel word = new JPanel();
        word.setLayout(new BoxLayout(word, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Recipes List");
        //greeting.setHorizontalAlignment(JLabel.CENTER); // Vertical central the label in BorderLayout
        // Set Font size
        title.setFont(new Font(title.getFont().getFontName(), title.getFont().getStyle(), 36));

        JLabel subtitle = new JLabel("X Recipes in the database");
        //help_word.setHorizontalAlignment(JLabel.CENTER);
        // Set Font size
        subtitle.setFont(new Font(subtitle.getFont().getFontName(), subtitle.getFont().getStyle(), 16));
        word.add(title);
        word.add(subtitle);
//        word.setBorder(new EmptyBorder(0,0,0,0));
        jp2.add(word, BorderLayout.PAGE_START);
        String[] columnNames = {"First Name", "Last Name", ""};
        Object[][] data =
                {
                        {"Homer", "Simpson", "delete Homer"},
                        {"Madge", "Simpson", "delete Madge"},
                        {"Bart",  "Simpson", "delete Bart"},
                        {"Lisa",  "Simpson", "delete Lisa"},
                };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable( model );
        //ButtonColumn buttonColumn = new ButtonColumn(table, delete, 2);
        // TODO: still need a better solution
        // TODO: Warning pop up when hitting delete
        jp2.add(table);
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.add(table);
//        jp2.add(scrollPane);
        this.add(jp2, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}