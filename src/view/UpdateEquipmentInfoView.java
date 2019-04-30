package view;

import controller.UpdateEquipmentInforController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEquipmentInfoView extends View{
    private UpdateEquipmentInforController c;

    public UpdateEquipmentInfoView(UpdateEquipmentInforController c){
        this.c = c;
        this.setTitle("Brew Day! - Equipment Update"); // set frame title
        this.setSize(600, 250); // set frame size
        this.setLayout(new BorderLayout());

        JPanel jp_header = new JPanel();
        jp_header.setLayout(new FlowLayout(FlowLayout.LEFT));

        /* back button*/
        JButton btn_back = new JButton("back");
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //It seems that it doesn't work
                c.goBack();
            }
        });
        jp_header.add(btn_back);

        JLabel msg_header = new JLabel("Update Equipment Information");
        msg_header.setFont(new Font(msg_header.getFont().getFontName(), msg_header.getFont().getStyle(), 24));
        jp_header.add(msg_header);
        this.add(jp_header, BorderLayout.NORTH);


        JPanel jp_main = new JPanel();
        jp_main.setLayout(new BoxLayout(jp_main, BoxLayout.Y_AXIS));// Vertically display
        jp_main.setBorder(new EmptyBorder(0,0,350,0));
        JTextField textfield_Model = new JTextField("Model");
        JTextField textfiled_Material = new JTextField("Material");
        JTextField textfield_Volumn = new JTextField("Volume");
        JTextField textfield_Purchase_Date = new JTextField("Purchase Date");
        //TODO: can replace these column with last equipment information

        jp_main.add(textfield_Model);
        jp_main.add(textfiled_Material);
        jp_main.add(textfield_Volumn);
        jp_main.add(textfield_Purchase_Date);
        this.add(jp_main, BorderLayout.CENTER);

        JPanel jp_foot = new JPanel();
        jp_foot.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp_foot.setBorder(new EmptyBorder(0,0,0,0));
        JButton btn_save = new JButton("save");
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.saveEquipmentInfo(textfield_Model.getText(),textfiled_Material.getText(),textfield_Volumn.getText(),textfield_Purchase_Date.getText());
                //update();
            }
        });
        jp_foot.add(btn_save);
        this.add(jp_foot, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        //repaint();
    }
}
