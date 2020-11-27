package dao;

import model.Role;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class RoleDAO {

    public Role createRole(Role newRole) {
        return insertEntity(newRole);
    }

    public List<Role> getRoles() {
        return getAllEntities(Role.class);
    }

    public void deleteRole(long id) {
        deleteByField(Role.class, "id", id);
    }

    public Role updateRole(Role updatedRole) {
        return updateEntity(updatedRole);
    }

    public List<Role> getRole(long id) {
        return getEntitiesByField("id", id, Role.class);
    }
}
