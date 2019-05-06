package controller;

import view.HomeView;
import view.UpdateEquipmentInfoView;

public class EquipmentInfoController {
    public EquipmentInfoController(){
    }

    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void turnUpdateInfo() {
        // TODO: Fill the exists Info to the field
        UpdateEquipmentInfoController ueic = new UpdateEquipmentInfoController();
        UpdateEquipmentInfoView ueiv = new UpdateEquipmentInfoView(ueic);
        ueiv.setVisible(true);
    }
}
