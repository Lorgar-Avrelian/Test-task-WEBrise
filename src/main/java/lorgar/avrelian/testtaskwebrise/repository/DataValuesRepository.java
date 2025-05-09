package lorgar.avrelian.testtaskwebrise.repository;

import lorgar.avrelian.testtaskwebrise.dao.DataValues;
import lorgar.avrelian.testtaskwebrise.dao.SubscriptionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Victor Tokovenko
 */
@Repository
public interface DataValuesRepository extends JpaRepository<DataValues, Long> {
    Collection<DataValues> findAllBySubscription(SubscriptionData data);
}
