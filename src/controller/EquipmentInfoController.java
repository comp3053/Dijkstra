package controller;

import view.HomeView;
import view.UpdateEquipmentInfoView;

import java.util.ArrayList;
import java.util.List;

public class EquipmentInfoController {
    public EquipmentInfoController(){
    }

    // TODO: should return a list, here I write a void because I don't know how to return, yeah.
    public void ReadEquipmentInfo(){
        List<String> info = new ArrayList<String>();
        info.add("Nya");
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
