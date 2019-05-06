package controller;

import utils.EmptyEquipmentNameException;
import model.Equipment;
import utils.InvalidEquipmentVolumeException;
import utils.FetchDataException;
import view.EquipmentInfoView;

import javax.swing.*;

public class UpdateEquipmentInfoController {
    private Equipment m;

    public UpdateEquipmentInfoController(Equipment m) {
        this.m = m;
    }

    public boolean saveEquipmentInfo(String Name, String Volumn) throws EmptyEquipmentNameException, InvalidEquipmentVolumeException, NumberFormatException {
        this.m = new Equipment(Name, Integer.parseInt(Volumn));
        System.out.println("The Equipment information are:" + " " + Name + " " + Volumn);
        int isSave = JOptionPane.showConfirmDialog(null,
                "Modify equipment information will change your default batch size, are you sure to modify?",
                "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (isSave == JOptionPane.YES_OPTION) {
            return m.update();
        } else {
            return false;
        }
    }

    public void goBack() {
        try {
            Equipment equipment;
            equipment = Equipment.getEquipment(1);
            EquipmentInfoController eic = new EquipmentInfoController(equipment);
            EquipmentInfoView ei = new EquipmentInfoView(eic, equipment);
            equipment.setModelListener(ei);
            ei.setVisible(true);
        } catch (FetchDataException | EmptyEquipmentNameException | InvalidEquipmentVolumeException e) {
            e.printStackTrace();
        }
    }
}
