package org.example;

public class Car extends Vehicle {
    private int doorCount;

    public Car(String licensePlate, int usageCount, boolean isRacing, int doorCount) {
        super(licensePlate, usageCount, isRacing);
        this.doorCount = doorCount;
    }

    public int getDoorCount() {
        return doorCount;
    }

    @Override
    public String getType() {
        return "Car";
    }
}