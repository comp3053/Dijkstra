package view;

import javax.swing.*;
import java.awt.*;

public class MissingIngredientsListView extends View{
    public MissingIngredientsListView(){
        this.setTitle("Missing Ingredient List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        JButton btn_back = new JButton("back");
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Missing Ingredient List For Recipe" + "A");
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.PAGE_START);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
        JLabel msg_headName = new JLabel("brew history" + "03");
        jp_main.add(msg_headName);
        JLabel msg_headUnit = new JLabel("for brewing record");
        jp_main.add(msg_headUnit);
        JLabel msg_headAmount = new JLabel("delete");
        jp_main.add(msg_headAmount);
        for(int i =0;i<5;i++) {
            JLabel msg_ingreNamei = new JLabel("brew history" + "03");
            jp_main.add(msg_ingreNamei);
            JLabel msg_ingreUniti = new JLabel("for brewing record");
            jp_main.add(msg_ingreUniti);
            JLabel msg_ingreAmounti = new JLabel("delete");
            jp_main.add(msg_ingreAmounti);
        }
        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout());

        JButton btn_OK = new JButton("OK");
        jp_foot.add(btn_OK);

        this.add(jp_foot, BorderLayout.LINE_END);
    }

    @Override
    public void update() {
        //repaint();
    }
}
