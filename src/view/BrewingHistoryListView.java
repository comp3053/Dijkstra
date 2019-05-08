package view;

import controller.BrewingHistoryListController;
import model.BrewingRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrewingHistoryListView extends View {
    private BrewingHistoryListController c;
    private ArrayList<BrewingRecord> BrewingRecords;
    public BrewingHistoryListView(BrewingHistoryListController c){
        this.BrewingRecords = c.readBrewingHistoryList();
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
                dispose();
            }
        });

        this.add(topLeftButtonBar, BorderLayout.NORTH);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        for(BrewingRecord brewingRecord: BrewingRecords) {
            JPanel mainPanelIter = new JPanel();
            mainPanelIter.setLayout(new FlowLayout());
            JLabel historyID = new JLabel("Brew History " + brewingRecord.getID());
            mainPanelIter.add(historyID);
            JLabel timeText = new JLabel(String.valueOf(brewingRecord.getBrewDate()));
            mainPanelIter.add(timeText);
            JButton btn_takeNote = new JButton("Take note");
            btn_takeNote.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.takeNote(brewingRecord);
                    dispose();
                }
            });
            mainPanelIter.add(btn_takeNote);
            mainPanel.add(mainPanelIter);
        }
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //repaint();
    }
}
