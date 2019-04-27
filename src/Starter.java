import view.HomeView;
import view.UpdateEquipmentInfoView;

public class Starter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // run that code on the event dispatch thread
            @Override
            public void run() {
                UpdateEquipmentInfoView hv = new UpdateEquipmentInfoView();
                hv.setVisible(true);
            }
        });
    }
}
