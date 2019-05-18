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

    /**
     * Save latest information of equipment.
     * @param Name Name of equipment.
     * @param Volume Volume of equipment.
     * @param firstTime Whether an equipment is exist in database.
     * @return Whether the save operation has done successfully.
     * @throws EmptyNameException Throws when name of equipment is empty.
     * @throws InvalidInputException Throws when number of volume is less than 0.
     * @throws NumberFormatException Throws when format of volume is invalid.
     */
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

    /**
     * Go back to equipment detail view.
     */
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
