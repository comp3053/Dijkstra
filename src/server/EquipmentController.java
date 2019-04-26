package server;

import com.alibaba.fastjson.JSONObject;
import model.EmptyEquipmentNameException;
import model.Equipment;
import model.InvalidEquipmentVolumeException;

public class EquipmentController {
    protected String getEquipmentInfo() {
        JSONObject obj = new JSONObject();
        try {
            Equipment equipment = new Equipment("HOMEBREW-2019", 100);
            obj.put("equipment", equipment);
        } catch (InvalidEquipmentVolumeException | EmptyEquipmentNameException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
