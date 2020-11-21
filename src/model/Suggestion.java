package model;

/**
 * Created by MRK on 11/19/2020.
 */
public class Suggestion {

    private long id;
    private long userId;
    private long basketId;
    private long factorId;
    private long suggestionCategoryId; // holds top category id

    public Suggestion(long id, long userId, long basketId, long factorId, long suggestionCategoryId) {
        this.id = id;
        this.userId = userId;
        this.basketId = basketId;
        this.factorId = factorId;
        this.suggestionCategoryId = suggestionCategoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public long getFactorId() {
        return factorId;
    }

    public void setFactorId(long factorId) {
        this.factorId = factorId;
    }

    public long getSuggestionCategoryId() {
        return suggestionCategoryId;
    }

    public void setSuggestionCategoryId(long suggestionCategoryId) {
        this.suggestionCategoryId = suggestionCategoryId;
    }
}
