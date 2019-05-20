/**
 * This abstract class extends a JFrame and set the window size and exit action
 * implements the ModelListener Interface
 */
package view;

import controller.ModelListener;

import javax.swing.*;

public abstract class View extends JFrame implements ModelListener {

    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    @Override
    public abstract void update();
}
