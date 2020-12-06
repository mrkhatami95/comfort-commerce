package dao;

import model.Factor;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class FactorDAO {

    /**
     * @param startDate include
     * @param endDate   exclude
     * @return a list of matching factors
     */
    public static List<Factor> findFactorByDate(long startDate, long endDate) {

        if (startDate > endDate)
            throw new IllegalArgumentException("startDate must be <= endDate");

        return getEntitiesByRangeOfField(Factor.class, "date", startDate, endDate);
    }

    public Factor createFactor(Factor newFactor) {
        return insertEntity(newFactor);
    }

    public List<Factor> getFactors() {
        return findAllEntitiesByField(Factor.class, "", null);
    }

    public void deleteFactorById(long id) {
        deleteByField(Factor.class, "id", id);
    }

    public Factor updateFactor(Factor updatedFactor) {
        return updateEntity(updatedFactor);
    }

    public List<Factor> getFactor(long id) {
        return findAllEntitiesByField(Factor.class, "id", id);
    }
}
