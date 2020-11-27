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
        return getEntitiesByField("name", name, Product.class);
    }

    public Product createProduct(Product newProduct) {
        return insertEntity(newProduct);
    }

    public List<Product> getProducts() {
        return getAllEntities(Product.class);
    }

    public void deleteProduct(long id) {
        deleteByField(Product.class, "id", id);
    }

    public Product updateProduct(Product updatedProduct) {
        return updateEntity(updatedProduct);
    }

    public List<Product> getProduct(long id) {
        return getEntitiesByField("id", id, Product.class);
    }
}
