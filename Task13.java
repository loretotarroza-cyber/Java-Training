package Chapter5;

class Vehicle {
    String make;
    String model;
    int year;

        Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}

class Car extends Vehicle {
    int numberOfDoors;

        Car(String make, String model, int year, int numberOfDoors) {
        super(make, model, year);  // call parent constructor
        this.numberOfDoors = numberOfDoors;
    }

    
    void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}

public class TaskVehicle {
    public static void main(String[] args) {
        Car car = new Car("Honda", "Civic", 2025, 4);

        car.displayDetails();
    }
}
