package StepProjectBooking.user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoFileText implements DAOUser<User> {
    private List<User> users = new ArrayList<>();

    public UserDaoFileText(List<User> users) {
        this.users = users;
    }

    public UserDaoFileText() {
    }

    @Override
    public void SaveData(String fileName) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void LoadData(List<User> users) {

        if (this.users != null) {
            this.users.forEach(this::save);
        }

    }

    @Override
    public void ReadData(String fileName) {

        List<User> ListLoaded = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ListLoaded = (List<User>) objectInputStream.readObject();
            LoadData(ListLoaded);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void save(User item) {
        if (item.equals(null)) {
            throw new NullPointerException();
        } else if (this.users.contains(item)) {
            users.set(users.indexOf(item), item);
        } else {
            User user = new User(item.getName(), item.getPassword());
            users.add(item);
        }

    }


    @Override
    public User get(int id) {

        if (this.users == null || id < 0 || id > this.users.size()) {

            throw new IllegalArgumentException("Something went wrong");

        } else {
            return this.users.get(id);
        }
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }


    @Override
    public boolean delete(String name) {
        if (this.users == null) {
            throw new IllegalArgumentException("Something went wrong");
        } else {
            this.users.remove(name);
            return true;
        }
    }
}
