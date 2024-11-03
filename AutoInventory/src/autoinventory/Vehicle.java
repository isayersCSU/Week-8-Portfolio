package autoinventory;

public class Vehicle {
  String Make;
  String Model;
  String Color;
  int Year;
  int Milage;

  // Constructor that takes all vehicle information
  public Vehicle(String make, String model, String color, int year, int mileage) {
    this.Make = make;
    this.Model = model;
    this.Color = color;
    this.Year = year;
    this.Milage = mileage;
  }

  @Override
  public String toString() {
    return "Make: " + Make + ", Model: " + Model + ", Color: " + Color + ", Year: " + Year + ", Mileage: " + Milage;
  }
}