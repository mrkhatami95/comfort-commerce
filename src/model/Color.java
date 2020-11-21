package model;

/**
 * Created by MRK on 11/19/2020.
 */
public class Color {

    private long id;
    private String color;

    public Color(long id, String color) {
        this.id = id;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
