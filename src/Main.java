import dao.ProductDAO;
import model.Product;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Shop");
//        ProductDAO dao = new ProductDAO();
//        System.out.println(dao.getProduct(1));


        Product newProduct = new Product();
        newProduct.setId(7);
        newProduct.setName("laptop");
        newProduct.setDescription("asus");
        newProduct.setPrice(150_000_000);
        newProduct.setColorId(12);
        newProduct.setDiscountId(3);
        newProduct.setCount(15);
        newProduct.setCategoryId(222);
        newProduct.setCommentId(341);

        ProductDAO dao = new ProductDAO();
        System.out.println(dao.updateProduct(newProduct));

    }
}
