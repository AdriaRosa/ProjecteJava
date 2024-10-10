package org.example;

public abstract class Vehicle {
    protected String licensePlate;
    protected int usageCount;
    protected boolean isRacing;

    public Vehicle(String licensePlate, int usageCount, boolean isRacing) {
        this.licensePlate = licensePlate;
        this.usageCount = usageCount;
        this.isRacing = isRacing;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public boolean isRacing() {
        return isRacing;
    }

    public abstract String getType();
}
