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
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(2020, 12, 10, 13, 15, 40));
        newFactor.setDate(timestamp.getTime());

        FactorDAO dao = new FactorDAO();
        dao.createFactor(newFactor);

        FactorDAO.findFactorByDate(
                Timestamp.valueOf("2020-12-10 13:15:35.0").getTime(),
                Timestamp.valueOf("2020-12-10 13:15:45.0").getTime())
                .forEach(System.out::println);
        System.out.println("--------------");
        dao.getFactors().forEach(System.out::println);

    }
}
