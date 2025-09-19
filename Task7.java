import java.util.Scanner;

public class ArithmeticOperations {
    // Addition
    public static int add(int x, int y) {
        return x + y;
    }
    // Subtraction
    public static int subtract(int x, int y) {
        return x - y;
    }
    // Multiplication
    public static int multiply(int x, int y) {
        return x * y;
    }
    // Division
    public static double divide(int x, int y) {
        if (y == 0) {
            System.out.println("Zero denominator is not allowed!");
            return 0;
        }
        return (double) x / y;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input two numbers
        System.out.print("Put your first number: ");
        int num1 = sc.nextInt();
        System.out.print("Put your second number: ");
        int num2 = sc.nextInt();

        // Arithmetic operations
        System.out.println("Addition: " + add(num1, num2));
        System.out.println("Subtraction: " + subtract(num1, num2));
        System.out.println("Multiplication: " + multiply(num1, num2));
        System.out.println("Division: " + divide(num1, num2));

        sc.close();
    }
}
