package controller;

import model.EmptyEquipmentNameException;
import model.Equipment;
import model.InvalidEquipmentVolumeException;
import view.EquipmentInfoView;

import javax.swing.*;

public class UpdateEquipmentInforController {
    public UpdateEquipmentInforController(){
        // Nothing to do
    }

    public boolean saveEquipmentInfo(String Name, String Volumn) throws EmptyEquipmentNameException, InvalidEquipmentVolumeException {
        System.out.println("The Equipment information are:" + " " + Name + " " + Volumn);
        int isSave = JOptionPane.showConfirmDialog(null,"Modify equipment information will change your default batch size, are you sure to modify?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        if (isSave == JOptionPane.YES_OPTION) {
            int VolumnInt = Integer.parseInt(Volumn);
            Equipment equipment = new Equipment(Name,VolumnInt);
            EquipmentController ec = new EquipmentController();
            return ec.update(equipment);
        } else {
            return false;
        }
    }

    public void goBack() {
        System.out.println("Go back");
        EquipmentInfoController eic = new EquipmentInfoController();
        EquipmentInfoView ei = new EquipmentInfoView(eic);
        ei.setVisible(true);
    }
}
