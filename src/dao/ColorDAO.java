package dao;

import model.Color;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class ColorDAO {

    public Color createColor(Color newColor) {
        return insertEntity(newColor);
    }

    public List<Color> getColors() {
        return getAllEntities(Color.class);
    }

    public void deleteColor(long id) {
        deleteByField(Color.class, "id", id);
    }

    public Color updateColor(Color updatedColor) {
        return updateEntity(updatedColor);
    }

    public List<Color> getColor(long id) {
        return getEntitiesByField("id", id, Color.class);
    }
}
