package lorgar.avrelian.testtaskwebrise.repository;

import lorgar.avrelian.testtaskwebrise.dao.SubscriptionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Victor Tokovenko
 */
@Repository
public interface SubscriptionsDataRepository extends JpaRepository<SubscriptionData, Integer> {
}
