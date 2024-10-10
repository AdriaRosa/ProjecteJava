package org.example;

public class Bicycle extends Vehicle {
    private boolean isElectric;

    public Bicycle(int usageCount, boolean isRacing, boolean isElectric) {
        super("ESPECIAL", usageCount, isRacing);
        this.isElectric = isElectric;
    }

    public boolean isElectric() {
        return isElectric;
    }

    @Override
    public String getType() {
        return "Bicycle";
    }
}