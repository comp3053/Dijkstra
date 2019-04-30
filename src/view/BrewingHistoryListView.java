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

        JPanel topLeftButtonBar = new JPanel();
        topLeftButtonBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button = new JButton("< Back");
        topLeftButtonBar.add(button);
        JLabel headerTitle = new JLabel("Add note - select brew history");
        headerTitle.setFont(new Font(headerTitle.getFont().getFontName(), headerTitle.getFont().getStyle(), 24));
        topLeftButtonBar.add(headerTitle);
        topLeftButtonBar.add(Box.createHorizontalGlue());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.goBack();
            }
        });

        this.add(topLeftButtonBar, BorderLayout.NORTH);

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
