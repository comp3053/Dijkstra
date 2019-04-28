package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EquipmentInfoView extends View{
    public EquipmentInfoView(){
        this.setTitle("Equipment Information"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        /* back button*/
        JButton btn_back = new JButton("back");
        btn_back.setBounds(50,50,100,50);
        jp_header.add(btn_back);


        this.add(jp_header, BorderLayout.PAGE_START);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
        JLabel msg_model = new JLabel("Model" + "HOMEBREW-2018");
        JLabel msg_material = new JLabel("Model" + "HOMEBREW-2018");
        JLabel msg_volume = new JLabel("Model" + "HOMEBREW-2018");
        JLabel msg_purchase_date = new JLabel("Model" + "HOMEBREW-2018");
        msg_model.setFont(new Font(msg_model.getFont().getFontName(), msg_model.getFont().getStyle(), 24));
        jp_main.add(msg_model);
        jp_main.add(msg_material);
        jp_main.add(msg_volume);
        jp_main.add(msg_purchase_date);
        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        JButton btn_update = new JButton("update");
        jp_foot.add(btn_update);
        this.add(jp_foot, BorderLayout.LINE_END);
    }

    @Override
    public void update() {
        //repaint();
    }
}