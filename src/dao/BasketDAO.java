package dao;

import model.Basket;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class BasketDAO {

    public Basket createBasket(Basket newBasket) {
        return insertEntity(newBasket);
    }

    public List<Basket> getBaskets() {
        return getEntitiesByField(Basket.class, "", null);
    }

    public void deleteBasket(long id) {
        deleteByField(Basket.class, "id", id);
    }

    public Basket updateBasket(Basket updatedBasket) {
        return updateEntity(updatedBasket);
    }

    public List<Basket> getBasket(long id) {
        return getEntitiesByField(Basket.class, "id", id);
    }
}
