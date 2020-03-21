package StepProjectBooking.DAO;

import java.util.List;


public interface DAOUser<T> {

    void SaveData(String fileName);

    void ReadData(String fileName);

    void LoadData(List<T> users);

    List<T> getAll();

    void save(T user);

    boolean delete(String name);

    T get(int id);

}
