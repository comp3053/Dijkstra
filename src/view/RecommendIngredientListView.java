package view;

import javax.swing.*;
import java.awt.*;

public class RecommendIngredientListView extends View{
    public RecommendIngredientListView(){
        this.setTitle("Recommend Recipe List"); // set frame title
        this.setSize(800, 600); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout());

        JButton btn_back = new JButton("back");
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Missing Ingredient List For Recipe" + "A");
        jp_header.add(msg_header);

        JLabel msg_header2 = new JLabel("Ingredient Not Enough");
        jp_header.add(msg_header2);

        this.add(jp_header, BorderLayout.PAGE_START);

        JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
        for(int i =0;i<5;i++) {
            JRadioButton msg_ingreNamei = new JRadioButton("Recipe E" + "11" + "Ingredient in use");
            jp_main.add(msg_ingreNamei);
        }
        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout());

        JButton btn_generate = new JButton("Generate Shopping List");
        jp_foot.add(btn_generate);

        this.add(jp_foot, BorderLayout.LINE_END);
    }
    @Override
    public void update() {
        //repaint();
    }
}
