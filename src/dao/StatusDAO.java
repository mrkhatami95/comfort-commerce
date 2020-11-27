package dao;

import model.Status;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class StatusDAO {

    public Status createStatus(Status newStatus) {
        return insertEntity(newStatus);
    }

    public List<Status> getStatuses() {
        return getAllEntities(Status.class);
    }

    public void deleteStatus(int id) {
        deleteByField(Status.class, "id", id);
    }

    public Status updateStatus(Status updatedStatus) {
        return updateEntity(updatedStatus);
    }

    public List<Status> getStatus(int id) {
        return getEntitiesByField("id", id, Status.class);
    }
}
