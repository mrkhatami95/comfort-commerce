package dao;

import model.Factor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public static List<Factor> getFactorsByRangeOfDates(LocalDateTime startDate, LocalDateTime endDate) {

        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("startDate must be <= endDate");

        long from = Timestamp.valueOf(startDate).getTime();
        long to = Timestamp.valueOf(endDate).getTime();

        return getEntitiesByRangeOfField("date", from, to, Factor.class);
    }

    public Factor createFactor(Factor newFactor) {
        return insertEntity(newFactor);
    }

    public List<Factor> getFactors() {
        return getEntitiesByField(Factor.class, "", null);
    }

    public void deleteFactorById(long id) {
        deleteByField(Factor.class, "id", id);
    }

    public Factor updateFactor(Factor updatedFactor) {
        return updateEntity(updatedFactor);
    }

    public List<Factor> getFactor(long id) {
        return getEntitiesByField(Factor.class, "id", id);
    }
}
