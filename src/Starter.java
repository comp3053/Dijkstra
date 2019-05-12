import controller.*;
import model.Equipment;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.*;

import javax.swing.*;


public class Starter {
    public static void main(String[] args) {
        // Run that code on the event dispatch thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                Equipment equipment = Equipment.getEquipment(1);
                HomeController hc = new HomeController();
                HomeView hv = new HomeView(hc);
                hv.setVisible(true);
            } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
                int isSave = JOptionPane.showConfirmDialog(null,
                        "There is no equipment information in database, would you like to add one?",
                        "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (isSave == JOptionPane.YES_OPTION) {
                    Equipment m = null;
                    try {
                        m = new Equipment("dummy", 1000);
                    } catch (InvalidInputException | EmptyNameException ex) {
                        ex.printStackTrace();
                    }
                    UpdateEquipmentInfoController ueic = new UpdateEquipmentInfoController(m);
                    UpdateEquipmentInfoView ueiv = new UpdateEquipmentInfoView(ueic, m, true);
                    ueiv.setVisible(true);
                } else {
                    System.exit(-1);
                }

            }
        });
    }
}
