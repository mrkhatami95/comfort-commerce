package model;

/**
 * Created by MRK on 11/19/2020.
 */
public class Discount {

    private long id;
    private String code;
    private long discountValue;
    private boolean isUsed;

    public Discount(long id, String code, long discountValue, boolean isUsed) {
        this.id = id;
        this.code = code;
        this.discountValue = discountValue;
        this.isUsed = isUsed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(long discountValue) {
        this.discountValue = discountValue;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
