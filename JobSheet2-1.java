import java.util.*;

class User {
    private int id;
    private String name;
    private String email;
    private String number;
    private String pin;

    public User(int id, String name, String email, String number, String pin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.pin = pin;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getNumber() { return number; }
    public String getPin() { return pin; }
    public void setPin(String newPin) { this.pin = newPin; }
}

public class UserAuthentication {
    private Map<String, User> users = new HashMap<>();  // key: email
    private User currentUser = null;
    private int userIdCounter = 1;

    // Registration method
    public boolean register(String name, String email, String number, String pin) {
        if (name.isEmpty() || email.isEmpty() || number.isEmpty() || pin.isEmpty()) {
            System.out.println("All fields must be filled.");
            return false;
        }
        if (users.containsKey(email)) {
            System.out.println("Email already registered.");
            return false;
        }

        User newUser = new User(userIdCounter++, name, email, number, pin);
        users.put(email, newUser);
        System.out.println("Registration successful!");
        return true;
    }

    // Login method
    public boolean login(String email, String pin) {
        if (!users.containsKey(email)) {
            System.out.println("User not found.");
            return false;
        }
        User user = users.get(email);
        if (user.getPin().equals(pin)) {
            currentUser = user;
            System.out.println("Login successful. Welcome, " + user.getName());
            return true;
        } else {
            System.out.println("Incorrect PIN.");
            return false;
        }
    }

    // Change PIN
    public boolean changePin(String oldPin, String newPin) {
        if (currentUser == null) {
            System.out.println("No user logged in.");
            return false;
        }
        if (!currentUser.getPin().equals(oldPin)) {
            System.out.println("Old PIN does not match.");
            return false;
        }
        currentUser.setPin(newPin);
        System.out.println("PIN changed successfully.");
        return true;
    }

    // Logout
    public void logout() {
        if (currentUser != null) {
            System.out.println("User " + currentUser.getName() + " logged out.");
            currentUser = null;
        } else {
            System.out.println("No user logged in.");
        }
    }
}
