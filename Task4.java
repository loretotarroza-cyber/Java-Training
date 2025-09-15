import java.util.Scanner;

public class PalindromeCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String input = scanner.nextLine();
        StringBuilder sb = new StringBuilder(input);
        String reversed = sb.reverse().toString();
        if (input.equals(reversed)) {
            System.out.println("The input name is a palindrome.");
        } else {
            System.out.println("The input name is not a palindrome.");
        }

        scanner.close();
    }
}
