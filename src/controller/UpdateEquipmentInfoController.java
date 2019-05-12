package controller;

import model.Equipment;
import utils.EmptyNameException;
import utils.InvalidInputException;
import utils.FetchDataException;
import view.EquipmentInfoView;

import javax.swing.*;

public class UpdateEquipmentInfoController {
    private Equipment m;

    public UpdateEquipmentInfoController(Equipment m) {
        this.m = m;
    }

    public boolean saveEquipmentInfo(String Name, String Volume, boolean firstTime) throws EmptyNameException, InvalidInputException, NumberFormatException {
        this.m = new Equipment(Name, Integer.parseInt(Volume));
        int isSave = JOptionPane.showConfirmDialog(null,
                "Modify equipment information will change your default batch size, are you sure to modify?",
                "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (isSave == JOptionPane.YES_OPTION) {
            if (firstTime) {
                return m.insert();
            } else {
                return m.update();
            }
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
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
