package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BrewingHistoryListView extends View {
    public BrewingHistoryListView(){
        this.setTitle("BrewingHistoryListView"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        /* back button*/
        JButton btn_back = new JButton("back");
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Add note - select brew history");
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.PAGE_START);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
        for(int i =0;i<5;i++) {
            JLabel msg_historyi = new JLabel("brew history" + "03");
            jp_main.add(msg_historyi);
            JLabel msg_time = new JLabel("2019-04-28");
            jp_main.add(msg_time);
            JButton btn_takeNote = new JButton("Take note");
            jp_main.add(btn_takeNote);
        }
        this.add(jp_main, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
