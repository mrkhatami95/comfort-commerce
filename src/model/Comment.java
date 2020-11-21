package model;

/**
 * Created by MRK on 11/19/2020.
 */
public class Comment {

    private long id;
    private String comment;

    public Comment(long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
