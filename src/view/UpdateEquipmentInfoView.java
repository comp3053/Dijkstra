package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UpdateEquipmentInfoView extends View{
    public UpdateEquipmentInfoView(){
        this.setTitle("Equipment Update"); // set frame title
        this.setSize(600, 250); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

        /* back button*/
        JButton btn_back = new JButton("back");
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Update Equipment Information");
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        jp_header.add(msg_header);
        this.add(jp_header, BorderLayout.NORTH);


        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BoxLayout(jp_main, BoxLayout.Y_AXIS));// Vertically display
        jp_main.setBorder(new EmptyBorder(0,0,350,0));
        JTextField textfield_Model = new JTextField("Model");
        JTextField textfiled_Material = new JTextField("Material");
        JTextField textfield_Volumn = new JTextField("Volume");
        JTextField textfield_Purchase_Date = new JTextField("Purchase Date");

        jp_main.add(textfield_Model);
        jp_main.add(textfiled_Material);
        jp_main.add(textfield_Volumn);
        jp_main.add(textfield_Purchase_Date);
        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp_foot.setBorder(new EmptyBorder(0,0,0,0));
        JButton btn_save = new JButton("save");
        jp_foot.add(btn_save);
        this.add(jp_foot, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        //repaint();
    }
}
