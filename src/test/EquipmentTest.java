package test;

import model.Equipment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EquipmentTest {

    private static Equipment equipment = new Equipment("myEquipment",500);

    @Test
    public void getName() {
        assertEquals("myEquipment",equipment.getName());
    }

    @Test
    public void setName() {
        equipment.setName("yourEquipment");
        assertEquals("yourEquipment",equipment.getName());
    }

    @Test
    public void getVolume() {
        assertEquals(500,equipment.getVolume());
    }

    @Test
    public void setVolume() {
        equipment.setVolume(-200);
        assertEquals(500,equipment.getVolume());
        /*
            TODO: once the volume is successfully set, all "getVolume()" method will return the value was set
            Even though the "getVolume()" method is above "setVolume()" Try to fix that bugs.
        */

    }
}