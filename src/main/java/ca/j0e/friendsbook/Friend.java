package ca.j0e.friendsbook;

public class Friend {
    // Properties - final until mutability required
    private final String name;
    private final String phoneNumber;
    private final String email;

    // Constructor
    Friend(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    // Output name when printed
    public String toString() {
        return name;
    }
}
