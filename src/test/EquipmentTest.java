package test;

import utils.EmptyNameException;
import model.Equipment;
import utils.InvalidInputException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EquipmentTest {
    //initial part
    private static Equipment equipment;

    @Before
    public void setUp() {//set up the equipment class
        try {
            equipment = new Equipment("myEquipment", 500);
        } catch (InvalidInputException | EmptyNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getName() {//test whether it can get the name
        assertEquals("myEquipment", equipment.getName());
    }

    @Test
    public void setName() {//test whether it can set the name
        try {
            equipment.setName("yourEquipment");
        } catch (EmptyNameException e) {
            e.printStackTrace();
        }
        assertEquals("yourEquipment", equipment.getName());
        try {
            equipment.setName("");
        } catch (EmptyNameException e) {
            e.printStackTrace();
        }
        assertEquals("yourEquipment", equipment.getName());
    }

    @Test
    public void getVolume() {// test whether it can get the volume
        assertEquals(500, equipment.getVolume());
    }

    @Test
    public void setVolume() {// test whether it can set the volume
        try {
            equipment.setVolume(1000);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        assertEquals(1000, equipment.getVolume());
        try {
            equipment.setVolume(0);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        assertEquals(1000, equipment.getVolume());
        try {
            equipment.setVolume(-100);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        assertEquals(1000, equipment.getVolume());
    }
}