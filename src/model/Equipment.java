package model;

public class Equipment {
    private String name;
    private int volume;

    public Equipment(String name, int volume) throws InvalidEquipmentInputException, EmptyEquipmentNameException {
        setName(name);
        setVolume(volume);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws EmptyEquipmentNameException {
        if (name.isEmpty()){
           throw new EmptyEquipmentNameException("Equipment name cannot be empty!");
        }else {
            this.name = name;
        }
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) throws InvalidEquipmentInputException {
        if (volume > 0) {
            this.volume = volume;
        } else {
            throw new InvalidEquipmentInputException("Volume should be greater than 0!");
        }
    }
}