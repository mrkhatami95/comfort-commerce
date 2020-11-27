package dao;

import model.Suggestion;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class SuggestionDAO {

    public Suggestion createSuggestion(Suggestion newSuggestion) {
        return insertEntity(newSuggestion);
    }

    public List<Suggestion> getSuggestions() {
        return getAllEntities(Suggestion.class);
    }

    public void deleteSuggestion(long id) {
        deleteByField(Suggestion.class, "id", id);
    }

    public Suggestion updateSuggestion(Suggestion updatedSuggestion) {
        return updateEntity(updatedSuggestion);
    }

    public List<Suggestion> getSuggestion(long id) {
        return getEntitiesByField("id", id, Suggestion.class);
    }

}
