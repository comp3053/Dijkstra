import view.HomeView;

public class Starter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // run that code on the event dispatch thread
            @Override
            public void run() {
                HomeView hv = new HomeView();
                hv.setVisible(true);
            }
        });
    }
}
