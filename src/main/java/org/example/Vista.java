package org.example;

// Main.java
import java.util.Scanner;

public class Vista {
    public static void main(String[] args) {
        DataBase database = new DataBase();
        ParkingController controller = new ParkingController(database);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Añadir vehículo");
            System.out.println("2. Actualizar vehículo");
            System.out.println("3. Eliminar vehículo");
            System.out.println("4. Buscar vehículo por matrícula");
            System.out.println("5. Filtrar vehículos por tipo");
            System.out.println("6. Filtrar vehículos por estado de competición");
            System.out.println("7. Salir");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Lógica para añadir un vehículo
                    System.out.println("Introduce el tipo de vehículo (Coche/Moto/Bicicleta):");
                    String vehicleType = scanner.next();

                    System.out.println("Introduce la matrícula:");
                    String newLicensePlate = scanner.next();

                    System.out.println("Introduce el número de usos:");
                    int usageCount = scanner.nextInt();

                    System.out.println("¿Es un vehículo de competición? (true/false):");
                    boolean isRacing = scanner.nextBoolean();

                    Vehicle newVehicle = null;

                    if ("Coche".equalsIgnoreCase(vehicleType)) {
                        System.out.println("Introduce el número de puertas:");
                        int doorCount = scanner.nextInt();
                        newVehicle = new Car(newLicensePlate, usageCount, isRacing, doorCount);
                    } else if ("Moto".equalsIgnoreCase(vehicleType)) {
                        System.out.println("¿Tiene baúl para cascos? (true/false):");
                        boolean hasHelmetBox = scanner.nextBoolean();
                        newVehicle = new Motorcycle(newLicensePlate, usageCount, isRacing, hasHelmetBox);
                    } else if ("Bicicleta".equalsIgnoreCase(vehicleType)) {
                        System.out.println("¿Es eléctrica? (true/false):");
                        boolean isElectric = scanner.nextBoolean();
                        newVehicle = new Bicycle(usageCount, isRacing, isElectric);
                    }

                    if (newVehicle != null) {
                        controller.addVehicle(newVehicle);
                        System.out.println("Vehículo añadido correctamente.");
                    } else {
                        System.out.println("Tipo de vehículo no válido.");
                    }
                    break;

                case 2:
                    // Lógica para actualizar un vehículo
                    System.out.println("Introduce la matrícula del vehículo a actualizar:");
                    String updateLicensePlate = scanner.next();

                    Vehicle existingVehicle = controller.searchVehicleByLicensePlate(updateLicensePlate);
                    if (existingVehicle != null) {
                        System.out.println("Vehículo encontrado: " + existingVehicle.getType());

                        System.out.println("Introduce el nuevo número de usos:");
                        int newUsageCount = scanner.nextInt();

                        System.out.println("¿Es un vehículo de competición? (true/false):");
                        boolean newIsRacing = scanner.nextBoolean();

                        if (existingVehicle instanceof Car) {
                            System.out.println("Introduce el nuevo número de puertas:");
                            int newDoorCount = scanner.nextInt();
                            existingVehicle = new Car(updateLicensePlate, newUsageCount, newIsRacing, newDoorCount);
                        } else if (existingVehicle instanceof Motorcycle) {
                            System.out.println("¿Tiene baúl para cascos? (true/false):");
                            boolean newHasHelmetBox = scanner.nextBoolean();
                            existingVehicle = new Motorcycle(updateLicensePlate, newUsageCount, newIsRacing, newHasHelmetBox);
                        } else if (existingVehicle instanceof Bicycle) {
                            System.out.println("¿Es eléctrica? (true/false):");
                            boolean newIsElectric = scanner.nextBoolean();
                            existingVehicle = new Bicycle(newUsageCount, newIsRacing, newIsElectric);
                        }

                        controller.updateVehicle(updateLicensePlate, existingVehicle);
                        System.out.println("Vehículo actualizado correctamente.");
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;

                case 3:
                    // Lógica para eliminar un vehículo
                    System.out.println("Introduce la matrícula del vehículo a eliminar:");
                    String deleteLicensePlate = scanner.next();

                    Vehicle vehicleToDelete = controller.searchVehicleByLicensePlate(deleteLicensePlate);
                    if (vehicleToDelete != null) {
                        controller.deleteVehicle(deleteLicensePlate);
                        System.out.println("Vehículo eliminado correctamente.");
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Introduce la matrícula:");
                    String licensePlate = scanner.next();
                    Vehicle foundVehicle = controller.searchVehicleByLicensePlate(licensePlate);
                    if (foundVehicle != null) {
                        System.out.println("Vehículo encontrado: " + foundVehicle.getType());
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Introduce el tipo de vehículo (Coche/Moto/Bicicleta):");
                    String type = scanner.next();
                    controller.filterVehiclesByType(type).forEach(v -> System.out.println(v.getLicensePlate()));
                    break;

                case 6:
                    System.out.println("Filtrando vehículos por estado de competición...");
                    System.exit(0);
                    break;

                case 7:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
