package controller;

import view.HomeView;
import view.UpdateEquipmentInfoView;

import java.util.ArrayList;
import java.util.List;

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
        UpdateEquipmentInforController ueic = new UpdateEquipmentInforController();
        UpdateEquipmentInfoView ueiv = new UpdateEquipmentInfoView(ueic);
        ueiv.setVisible(true);
    }
}
