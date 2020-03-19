package StepProjectBooking.user;

import java.util.List;

public class UserService implements DAOUser<User> {

    private DAOUser<User> userDAO =new UserDaoFileText();

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    public void save(User user) {

        if (!user.getName().equals("") && !user.getPassword().equals("")) {
            userDAO.save(user);
        } else {
            new IllegalArgumentException();
        }
    }

    @Override
    public boolean delete(String  name) {
        userDAO.delete(name);
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    public void SaveData(String fileName) {
        userDAO.SaveData(fileName);
    }

    public void ReadData(String fileName) {
        userDAO.ReadData(fileName);
    }

    @Override
    public void LoadData(List<User> users) {}
}
