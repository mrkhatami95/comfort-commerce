package dao;

import model.Product;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class ProductDAO {

    public Product findProductByName(String name) {
        return getEntity("product", "name", name, new Product());
    }

    public Product createProduct(Product newProduct) {
        return insertEntity(newProduct);
    }

    public List<Product> getProducts() {
        return getListOfEntities("product", new Product());
    }

    public void deleteProduct(long id) {
        String sql = "DELETE FROM product where id = ?";
        delete(id, sql);
    }

    public Product updateProduct(Product updatedProduct) {
        return updateEntity(updatedProduct);
    }

    public Product getProduct(long id) {
        return getEntity("product", "id", id, new Product());
    }
}
