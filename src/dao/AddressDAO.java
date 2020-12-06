package dao;

import model.Address;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class AddressDAO {

    public Address createAddress(Address newAddress) {
        return insertEntity(newAddress);
    }

    public List<Address> getAddresses() {
        return getEntitiesByField(Address.class, "", null);
    }

    public void deleteAddress(long id) {
        deleteByField(Address.class, "id", id);
    }

    public Address updateAddress(Address updatedAddress) {
        return updateEntity(updatedAddress);
    }

    public List<Address> getAddress(long id) {
        return getEntitiesByField(Address.class, "id", id);
    }
}
