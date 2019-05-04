package controller;

import view.EquipmentInfoView;

import javax.swing.*;

public class UpdateEquipmentInforController {
    public UpdateEquipmentInforController(){
        // Nothing to do
    }

    public void saveEquipmentInfo(String Material, String Volumn)
    {
        System.out.println("The Equipment information are:" + " " + Material + " " + Volumn);
        int isSave = JOptionPane.showConfirmDialog(null,"Modify equipment information will change your default batch size, are you sure to modify?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        if (isSave == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Equipment Information have been saved");
        } else {
            JOptionPane.showMessageDialog(null, "Equipment Information will not be saved");
        }
    }

    public void goBack() {
        EquipmentInfoController eic = new EquipmentInfoController();
        EquipmentInfoView ei = new EquipmentInfoView(eic);
        ei.setVisible(true);
    }
}
