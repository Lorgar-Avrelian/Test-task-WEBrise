package lorgar.avrelian.testtaskwebrise.repository;

import lorgar.avrelian.testtaskwebrise.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Victor Tokovenko
 */
@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscription, Long> {
}
