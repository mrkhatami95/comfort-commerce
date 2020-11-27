package dao;

import model.Category;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class CategoryDAO {

    public Category createCategory(Category newCategory) {
        return insertEntity(newCategory);
    }

    public List<Category> getCategories() {
        return getAllEntities(Category.class);
    }

    public void deleteCategory(long id) {
        deleteByField(Category.class, "id", id);
    }


    public Category updateCategory(Category updatedCategory) {
        return updateEntity(updatedCategory);
    }

    public List<Category> getCategory(long id) {
        return getEntitiesByField("id", id, Category.class);
    }

}
