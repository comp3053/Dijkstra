package view;

import controller.ModelListener;
import model.Equipment;

import javax.swing.*;

public abstract class View extends JFrame implements ModelListener {

    public View(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public abstract void update();
}
