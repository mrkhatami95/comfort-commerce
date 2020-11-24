import dao.AddressDAO;
import dao.DAOManager;
import dao.UserDAO;
import model.Address;
import model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("Welcome to Shop!");
        System.out.println(Arrays.toString(Class.forName("model.User").getMethods()));
        System.out.println(Long.TYPE);
    }
}
