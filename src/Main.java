import dao.FactorDAO;
import model.Factor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Shop");

        Factor newFactor = new Factor();
        newFactor.setBasketId(11);
        newFactor.setDelivery(1);
        newFactor.setPrice(12_000_234);
        newFactor.setUserId(1300);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(2020, 12, 10, 13, 15, 30));
        newFactor.setDate(timestamp.getTime());

        FactorDAO dao = new FactorDAO();
        dao.createFactor(newFactor);

        FactorDAO.getFactorsByRangeOfDates(
                LocalDateTime.of(2020, 12, 10, 12, 25, 10),
                LocalDateTime.of(2020, 12, 10, 13, 0, 0))
                .forEach(System.out::println);

    }
}
