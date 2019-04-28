package view;

import javax.swing.*;
import java.awt.*;

public class EquipmentInfoView extends View{
    public EquipmentInfoView(){
        this.setTitle("Equipment Information"); // set frame title
        this.setSize(600, 200); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

        /* back button*/
        JButton btn_back = new JButton("back");
        jp_header.add(btn_back);
        JLabel msg_header = new JLabel("Equipment Information");
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.NORTH);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BoxLayout(jp_main, BoxLayout.Y_AXIS));
        JLabel msg_model = new JLabel("Model" + "HOMEBREW-2018");
        JLabel msg_material = new JLabel("Model" + "HOMEBREW-2018");
        JLabel msg_volume = new JLabel("Model" + "HOMEBREW-2018");
        JLabel msg_purchase_date = new JLabel("Model" + "HOMEBREW-2018");
        jp_main.add(msg_model);
        jp_main.add(msg_material);
        jp_main.add(msg_volume);
        jp_main.add(msg_purchase_date);
        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btn_update = new JButton("update");
        jp_foot.add(btn_update);
        this.add(jp_foot, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        //repaint();
    }
}