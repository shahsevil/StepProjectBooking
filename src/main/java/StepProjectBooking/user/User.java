package StepProjectBooking.user;

public class User {
    private String userName;
    private String password;
    private String name;
    private String surname;

    public User(String password, String name, String surname) {
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return String.format("User{UserName='%s', password='%s', name='%s', surname='%s'}", userName, password, name, surname);
    }

    public static User parse(String line) {
        String[] chunks = line.split("\\|");
        return new User(chunks[0],chunks[1]);
    }
}
