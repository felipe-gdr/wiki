package wiki.reactor.playgrounds.model;

public class User {
    public static User SKYLER = new User("swhite", "Skyler", "White");
    public static User JESSE = new User("jpinkman", "Jesse", "Pinkman");

    private final String username;
    private final String firstname;
    private final String lastname;

    public User(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
