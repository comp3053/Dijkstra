package server;

import com.alibaba.fastjson.JSON;
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

    protected String updateEquipmentInfo(String jsonString) {
        Equipment equipment = JSON.parseObject(jsonString, Equipment.class);
        JSONObject obj = new JSONObject();
        obj.put("name", equipment.getName());
        obj.put("volume", equipment.getVolume());
        obj.put("status", true);
        return obj.toString();
    }
}
