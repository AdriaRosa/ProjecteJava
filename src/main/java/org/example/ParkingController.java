package org.example;

// ParkingController.java
import java.util.List;

public class ParkingController {
    private DataBase database;

    public ParkingController(DataBase database) {
        this.database = database;
    }

    public void addVehicle(Vehicle vehicle) {
        database.addVehicle(vehicle);
    }

    public void updateVehicle(String licensePlate, Vehicle vehicle) {
        database.updateVehicle(licensePlate, vehicle);
    }

    public void deleteVehicle(String licensePlate) {
        database.removeVehicle(licensePlate);
    }

    public Vehicle searchVehicleByLicensePlate(String licensePlate) {
        return database.findVehicleByLicensePlate(licensePlate);
    }

    public List<Vehicle> filterVehiclesByType(String type) {
        return database.filterVehiclesByType(type);
    }

}

