package model;

/**
 * Created by MRK on 11/19/2020.
 */
public class Product {

    private long id;
    private String name;
    private String description;
    private long price;
    private long colorId;
    private long discountId;
    private long count; //available count
    private long categoryId;
    private long commentId;

    public Product() {

    }

    public Product(long id, String name, String description, long price, long colorId, long discountId, long count, long categoryId, long commentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.colorId = colorId;
        this.discountId = discountId;
        this.count = count;
        this.categoryId = categoryId;
        this.commentId = commentId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getColorId() {
        return colorId;
    }

    public void setColorId(long colorId) {
        this.colorId = colorId;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
}
