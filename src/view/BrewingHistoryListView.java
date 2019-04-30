package view;

import controller.BrewingHistoryListController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrewingHistoryListView extends View {
    private BrewingHistoryListController c;
    public BrewingHistoryListView(BrewingHistoryListController c){
        this.setTitle("Brew Day! - Brewing History"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

        /* back button */
        JButton btn_back = new JButton("back");
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
            }
        });
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Add note - select brew history");
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        jp_header.add(msg_header);

        this.add(jp_header, BorderLayout.NORTH);

        c.takeNote();

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BoxLayout(jp_main,BoxLayout.Y_AXIS));
        for(int i =0;i<5;i++) {
            JPanel jp_main_i = new JPanel();
            jp_main_i.setLayout(new FlowLayout());
            JLabel msg_historyi = new JLabel("brew history" + " 03 ");
            jp_main_i.add(msg_historyi);
            JLabel msg_time = new JLabel("2019-04-28 ");
            jp_main_i.add(msg_time);
            JButton btn_takeNote = new JButton("Take note");
            btn_takeNote.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.takeNote();
                }
            });
            jp_main_i.add(btn_takeNote);
            jp_main.add(jp_main_i);
        }
        this.add(jp_main, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
