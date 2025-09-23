package Chapter5;

// 1. Shape interface
interface Shape {
    double calculateArea();
    double calculatePerimeter();
}

// 2. AbstractShape
abstract class AbstractShape implements Shape {
    protected String color;
    protected double length;
    protected double width;

    // 2a. Constructor
    public AbstractShape(String color, double length, double width) {
        this.color = color;
        this.length = length;
        this.width = width;
    }

    // 2b. Abstract methods
    @Override
    public abstract double calculateArea();

    @Override
    public abstract double calculatePerimeter();
}

// 3a. Circle
class Circle extends AbstractShape {
    private double radius;

    public Circle(String color, double radius) {
        super(color, radius, radius); // Pass radius twice (length & width not relevant for circle)
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

// 3b. Rectangle class
class Rectangle extends AbstractShape {
    public Rectangle(String color, double length, double width) {
        super(color, length, width);
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}

// 4. Main class
public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle("Red", 9);
        Shape rectangle = new Rectangle("Blue", 20, 12);

        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calculatePerimeter());

        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Rectangle Perimeter: " + rectangle.calculatePerimeter());
    }
}
