package model;

/**
 * Created by MRK on 11/19/2020.
 */
public class Address {

    private long id; //todo : id could be ext.
    private String address;

    public Address() {
    }

    public Address(long id, String address) {
        this.id = id;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
