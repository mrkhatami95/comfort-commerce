package dao;

import model.Discount;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class DiscountDAO {

    public Discount createDiscount(Discount newDiscount) {
        return insertEntity(newDiscount);
    }

    public List<Discount> getDiscounts() {
        return getEntitiesByField(Discount.class, "", null);
    }

    public void deleteDiscount(long id) {
        deleteByField(Discount.class, "id", id);
    }

    public Discount updateDiscount(Discount updatedDiscount) {
        return updateEntity(updatedDiscount);
    }

    public List<Discount> getDiscount(long id) {
        return getEntitiesByField(Discount.class, "id", id);
    }
}
