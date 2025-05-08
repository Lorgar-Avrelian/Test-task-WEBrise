package lorgar.avrelian.testtaskwebrise.repository;

import lorgar.avrelian.testtaskwebrise.dao.SubscriptionData;
import lorgar.avrelian.testtaskwebrise.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Victor Tokovenko
 */
@Repository
public interface SubscriptionsDataRepository extends JpaRepository<SubscriptionData, Long> {
    List<SubscriptionData> findAllByUser(User user);
}
