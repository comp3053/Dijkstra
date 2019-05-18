package controller;

import model.Equipment;
import view.HomeView;
import view.UpdateEquipmentInfoView;

public class EquipmentInfoController {
    private Equipment m;

    public EquipmentInfoController(Equipment m) {
        this.m = m;
    }

    /**
     * Go back to home page.
     */
    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    /**
     * Go to update equipment page.
     */
    public void turnUpdateInfo() {
        UpdateEquipmentInfoController ueic = new UpdateEquipmentInfoController(m);
        UpdateEquipmentInfoView ueiv = new UpdateEquipmentInfoView(ueic, m, false);
        ueiv.setVisible(true);
    }
}
