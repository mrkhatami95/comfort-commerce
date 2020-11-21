import dao.AddressDAO;
import dao.DAOManager;
import model.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Address address = new Address();
        AddressDAO dao = new AddressDAO();
        address.setAddress("OK then we succeed");
        dao.createAddress(address);
        //dao.deleteAddress(1);
        System.out.println(dao.getAddress(1));
        address.setAddress("OK then we succeed after update");
        System.out.println(dao.updateAddress(address));
        dao.listAllAddress().stream().forEach(System.out::println);
    }
}
