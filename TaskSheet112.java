public class ArithmeticTask {
    public static void main(String[] args) {
        int a = 30;
        int b = 10;

        System.out.println("Initial value of a: " + a);

        // Addition assignment (+=)
        a += b; // a = a + b
        System.out.println("After a += b : " + a);

        // Subtraction assignment (-=)
        a -= b; // a = a - b
        System.out.println("After a -= b : " + a);

        // Multiplication assignment (*=)
        a *= b; // a = a * b
        System.out.println("After a *= b : " + a);

        // Division assignment (/=)
        a /= b; // a = a / b
        System.out.println("After a /= b : " + a);

        // Modulus assignment (%=)
        a %= b; // a = a % b
        System.out.println("After a %= b : " + a);
    }
}
