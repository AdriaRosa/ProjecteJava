package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private Connection connection;

    public DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (license_plate, usage_count, is_racing, type, extra_info) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getLicensePlate());
            stmt.setInt(2, vehicle.getUsageCount());
            stmt.setBoolean(3, vehicle.isRacing());
            stmt.setString(4, vehicle.getType());

            // Afegeix informació extra segons el tipus de vehicle
            if (vehicle instanceof Car) {
                stmt.setString(5, String.valueOf(((Car) vehicle).getDoorCount()));
            } else if (vehicle instanceof Motorcycle) {
                stmt.setString(5, String.valueOf(((Motorcycle) vehicle).hasHelmetBox()));
            } else if (vehicle instanceof Bicycle) {
                stmt.setString(5, String.valueOf(((Bicycle) vehicle).isElectric()));
            } else {
                stmt.setString(5, "N/A");
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) {
        String sql = "UPDATE vehicles SET usage_count = ?, is_racing = ?, type = ?, extra_info = ? WHERE license_plate = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, updatedVehicle.getUsageCount());
            stmt.setBoolean(2, updatedVehicle.isRacing());
            stmt.setString(3, updatedVehicle.getType());

            if (updatedVehicle instanceof Car) {
                stmt.setString(4, String.valueOf(((Car) updatedVehicle).getDoorCount()));
            } else if (updatedVehicle instanceof Motorcycle) {
                stmt.setString(4, String.valueOf(((Motorcycle) updatedVehicle).hasHelmetBox()));
            } else if (updatedVehicle instanceof Bicycle) {
                stmt.setString(4, String.valueOf(((Bicycle) updatedVehicle).isElectric()));
            } else {
                stmt.setString(4, "N/A");
            }

            stmt.setString(5, licensePlate);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicle(String licensePlate) {
        String sql = "DELETE FROM vehicles WHERE license_plate = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, licensePlate);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        String sql = "SELECT * FROM vehicles WHERE license_plate = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, licensePlate);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                int usageCount = rs.getInt("usage_count");
                boolean isRacing = rs.getBoolean("is_racing");
                String extraInfo = rs.getString("extra_info");

                // Crear el vehicle segons el tipus
                switch (type) {
                    case "Car":
                        return new Car(licensePlate, usageCount, isRacing, Integer.parseInt(extraInfo));
                    case "Motorcycle":
                        return new Motorcycle(licensePlate, usageCount, isRacing, Boolean.parseBoolean(extraInfo));
                    case "Bicycle":
                        return new Bicycle(usageCount, isRacing, Boolean.parseBoolean(extraInfo));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vehicle> filterVehiclesByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String licensePlate = rs.getString("license_plate");
                int usageCount = rs.getInt("usage_count");
                boolean isRacing = rs.getBoolean("is_racing");
                String extraInfo = rs.getString("extra_info");

                switch (type) {
                    case "Car":
                        vehicles.add(new Car(licensePlate, usageCount, isRacing, Integer.parseInt(extraInfo)));
                        break;
                    case "Motorcycle":
                        vehicles.add(new Motorcycle(licensePlate, usageCount, isRacing, Boolean.parseBoolean(extraInfo)));
                        break;
                    case "Bicycle":
                        vehicles.add(new Bicycle(usageCount, isRacing, Boolean.parseBoolean(extraInfo)));
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}
