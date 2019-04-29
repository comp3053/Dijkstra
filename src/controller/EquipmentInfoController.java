package controller;

import java.util.ArrayList;
import java.util.List;

public class EquipmentInfoController {
    public EquipmentInfoController(){
    }

    //should return a list, here I write a void because I don't know how to return, yeah.
    public void ReadEquipmentInfo(){
        List<String> infor = new ArrayList<String>();
        infor.add("Nya");
    }

    public void goBack() {
        System.out.println("go back");
    }

    public void turnUpdateInfo() {
        System.out.println("turn to update info");
    }
}
