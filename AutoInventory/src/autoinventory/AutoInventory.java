package autoinventory;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AutoInventory {

  public static void main(String[] args) {
    ArrayList<Vehicle> vehicles = new ArrayList<>(); 
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Enter vehicle information (Make, Model, Color, Year, Milage):");

      String make = getInputString(scanner, "Make");
      String model = getInputString(scanner, "Model");
      String color = getInputString(scanner, "Color");

      int year = getValidInteger(scanner, "Year (must be a valid year)");
      int mileage = getValidInteger(scanner, "Mileage (must be a number)");

      System.out.println("Vehicle added successfully!");
      vehicles.add(new Vehicle(make, model, color, year, mileage));

      System.out.println("Do you want to add another vehicle? (y/n)");
      String choice = scanner.nextLine();
      if (!choice.equalsIgnoreCase("y")) {
        break;
      }
    }

// Print the stored vehicle information
    System.out.println("\nList of Added Vehicles:");
    if (!vehicles.isEmpty()) {
      for (Vehicle vehicle : vehicles) {
        System.out.println(vehicle);
      }
    } else {
      System.out.println("No vehicles added yet.");
    }

// Offer to update a vehicle
    System.out.println("\nDo you want to update a vehicle? (y/n)");
    String choice = scanner.nextLine();
    if (choice.equalsIgnoreCase("y")) {
      updateVehicle(scanner, vehicles);
    }

// Print the updated list of vehicles
    System.out.println("\nUpdated List of Vehicles:");
    if (!vehicles.isEmpty()) {
      for (Vehicle vehicle : vehicles) {
        System.out.println(vehicle);
      }
    } else {
      System.out.println("No vehicles remaining.");
    }

// Print to Document
      System.out.println("\nDo you want to print the vehicle information to a document? (y/n)");
      choice = scanner.nextLine();
      if (choice.equalsIgnoreCase("y")) {
        printToDocument(vehicles);
        }

    scanner.close();
  }

// Method to handle String input with validation message
  private static String getInputString(Scanner scanner, String fieldName) {
    String input;
    while (true) {
      System.out.print(fieldName + ": ");
      input = scanner.nextLine();
      if (!input.isEmpty()) {
        break;
      }
      System.out.println("Please enter a value for " + fieldName);
    }
    return input;
  }

// Method to handle integer input with validation message
  private static int getValidInteger(Scanner scanner, String fieldName) {
    int value;
    while (true) {
      try {
        System.out.print(fieldName + ": ");
        value = scanner.nextInt();
        scanner.nextLine();
        break;
      } catch (InputMismatchException e) {
        System.out.println("Invalid input for " + fieldName + ". Please enter a number.");
        scanner.nextLine();
      }
    }
    return value;
  }

// Method to remove a vehicle from the list
  private static void removeVehicle(Scanner scanner, ArrayList<Vehicle> vehicles) {
    System.out.println("Enter the index of the vehicle to remove (starting from 1):");
    int indexToRemove = getValidInteger(scanner, "Index");

// Validate the index range
    if (indexToRemove < 1 || indexToRemove > vehicles.size()) {
      System.out.println("Invalid index. Please enter a valid index between 1 and " + vehicles.size());
      return;
    }

// Remove the vehicle at the specified index (remember that ArrayList indexes start from 0)
    vehicles.remove(indexToRemove - 1);
    System.out.println("Vehicle removed successfully!");
  }

// Method to update a vehicle's information
  private static void updateVehicle(Scanner scanner, ArrayList<Vehicle> vehicles) {
    System.out.println("Enter the index of the vehicle to update (starting from 1):");
    int indexToUpdate = getValidInteger(scanner, "Index");

// Validate the index range
    if (indexToUpdate < 1 || indexToUpdate > vehicles.size()) {
      System.out.println("Invalid index. Please enter a valid index between 1 and " + vehicles.size());
      return;
    }

// Get the vehicle to update
    Vehicle vehicleToUpdate = vehicles.get(indexToUpdate - 1);

    System.out.println("Enter the new information (leave blank to keep current value):");
    String newMake = getInputString(scanner, "Make");
    String newModel = getInputString(scanner, "Model");
    String newColor = getInputString(scanner, "Color");
    int newYear = getValidInteger(scanner, "Year (must be a valid year)");
    int newMileage = getValidInteger(scanner, "Mileage (must be a number)");

// Update the vehicle's information if any of the new values are not empty
    if (!newMake.isEmpty()) {
      vehicleToUpdate.Make = newMake;
    }
    if (!newModel.isEmpty()) {
      vehicleToUpdate.Model = newModel;
    }
    if (!newColor.isEmpty()) {
      vehicleToUpdate.Color = newColor;
    }
    if (newYear != 0) {
      vehicleToUpdate.Year = newYear;
    }
    if (newMileage != 0) {
      vehicleToUpdate.Milage = newMileage;
    }

    System.out.println("Vehicle updated successfully!");
  }
  
  // Method to print to document
    private static void printToDocument(ArrayList<Vehicle> vehicles) {
        System.out.println("** Printing Vehicle Information to document.txt... **");
        try (java.io.FileWriter writer = new java.io.FileWriter("document.txt")) {
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.toString() + "\n");
            }
            writer.flush();
            System.out.println("** Vehicle information printed successfully!**");
        } catch (java.io.IOException e) {
            System.err.println("Error occurred while printing to document: " + e.getMessage());
        }
    }
}