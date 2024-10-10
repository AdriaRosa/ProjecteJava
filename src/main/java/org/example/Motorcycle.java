package org.example;


public class Motorcycle extends Vehicle {
    private boolean hasHelmetBox;

    public Motorcycle(String licensePlate, int usageCount, boolean isRacing, boolean hasHelmetBox) {
        super(licensePlate, usageCount, isRacing);
        this.hasHelmetBox = hasHelmetBox;
    }

    public boolean hasHelmetBox() {
        return hasHelmetBox;
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }
}
