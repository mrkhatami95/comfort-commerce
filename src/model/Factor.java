package model;

import java.sql.Timestamp;

/**
 * Created by MRK on 11/19/2020.
 */
public class Factor {

    private long id;
    private long basketId;
    private long userId;
    private long price;
    private long date;
    private int delivery;

    public Factor(long id, long basketId, long userId, long price, long date, int delivery) {
        this.id = id;
        this.basketId = basketId;
        this.userId = userId;
        this.price = price;
        this.date = date;
        this.delivery = delivery;
    }

    public Factor() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Factor{" +
                "id=" + id +
                ", basketId=" + basketId +
                ", userId=" + userId +
                ", price=" + price +
                ", date=" + new Timestamp(date).toLocalDateTime() +
                ", delivery=" + delivery +
                '}';
    }
}
