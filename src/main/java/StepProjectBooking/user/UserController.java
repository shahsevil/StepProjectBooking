package StepProjectBooking.user;

public class UserController {
    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public void save(User item) {
        userService.save(item);
    }

    public void delete(String name) {
        userService.delete(name);
    }

    public User userByReg(String userName, String password) {
        return userService.getAll().stream().filter(
                users -> (users.getUserName().equals(userName) && users.getPassword().equals(password))
        ).findAny().orElse(null);
    }
}
