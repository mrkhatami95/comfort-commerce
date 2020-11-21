package model;

/**
 * Created by MRK on 11/19/2020.
 */
public class Basket {

    private long id;
    private long productId;
    private long userId;
    private long count;

    public Basket(long id, long productId, long userId, long count) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
