package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UpdateEquipmentInfoView extends View{
    public UpdateEquipmentInfoView(){
        this.setTitle("Equipment Update"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        /* back button*/
        JButton btn_back = new JButton("back");
        btn_back.setBounds(50,50,100,50);
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Update Equipment Information");
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        msg_header.setBounds(100,50,100,50);
        jp_header.add(msg_header);
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
