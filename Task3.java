public class Task3 {
    public static void main(String[] args) {
        String a = "Wow";
        String b = "Wow";
        String c = "Hi";
        String d = "Wow!";

        boolean b1 = a == b;              // true (same literal, same reference)
        boolean b2 = d.equals(b + "!");   // true ("Wow!" == "Wow!")
        boolean b3 = !c.equals(a);        // true ("Hi" != "Wow")

        if (b1 && b2 && b3) {
            System.out.println("Success!");
        }
    }
}
