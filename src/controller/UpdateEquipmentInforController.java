package controller;

import javax.swing.*;

public class UpdateEquipmentInforController {
    public UpdateEquipmentInforController(){
        // Nothing to do
    }

    public void saveEquipmentInfo(String Model, String Material, String Volumn, String Purchase_Date)
    {
        System.out.println("The Equipment information are:" + Model + Material + Volumn + Purchase_Date);
        int isSave = JOptionPane.showConfirmDialog(null,"Modify equipment information will change your default batch size, are you sure to modify?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        if (isSave == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Equipment Information have been saved");
        } else {
            JOptionPane.showMessageDialog(null, "Equipment Information will not be saved");
        }
    }

    public void goBack() {
        System.out.println("Go back");
        //Maybe I don't need to write this method
    }
}