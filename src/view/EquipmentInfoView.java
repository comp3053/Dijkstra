package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EquipmentInfoView extends View{
    public EquipmentInfoView(){
        this.setTitle("EquipmentUpdate"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        /* back button*/
        JButton btn_back = new JButton("back");
        btn_back.setBounds(50,50,100,50);
        jp_header.add(btn_back);

        JLabel header_msg = new JLabel("Update Equipment Information");
        header_msg.setFont(new Font(header_msg.getFont().getFontName(), header_msg.getFont().getStyle(), 24));
        header_msg.setBounds(100,50,100,50);
        jp_header.add(header_msg);
        this.add(jp_header, BorderLayout.PAGE_START);


        JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
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
        JButton btn_save = new JButton("save");
        jp_foot.add(btn_save);
        this.add(jp_foot, BorderLayout.LINE_END);
    }

    @Override
    public void update() {
        //repaint();
    }
}