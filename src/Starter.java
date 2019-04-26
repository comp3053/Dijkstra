import view.HomeFrame;

public class Starter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // run that code on the event dispatch thread
            @Override
            public void run() {
                HomeFrame hf = new HomeFrame();
                hf.setVisible(true);
            }
        });
    }
}
