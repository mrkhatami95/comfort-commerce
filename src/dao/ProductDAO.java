package dao;

import model.Product;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class ProductDAO {

    public List<Product> findProductByName(String name) {
        return findAllEntitiesByField(Product.class, "name", name);
    }

    public Product createProduct(Product newProduct) {
        return insertEntity(newProduct);
    }

    public List<Product> getProducts() {
        return findAllEntitiesByField(Product.class, "", null);
    }

    public void deleteProduct(long id) {
        deleteByField(Product.class, "id", id);
    }

    public Product updateProduct(Product updatedProduct) {
        return updateEntity(updatedProduct);
    }

    public List<Product> getProduct(long id) {
        return findAllEntitiesByField(Product.class, "id", id);
    }
}
