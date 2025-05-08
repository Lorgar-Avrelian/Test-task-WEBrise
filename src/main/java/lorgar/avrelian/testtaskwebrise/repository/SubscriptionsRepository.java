package lorgar.avrelian.testtaskwebrise.repository;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Victor Tokovenko
 */
@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findSubscriptionByTitleIgnoreCaseAndTariffIgnoreCase(String title, String tariff);

    Optional<Subscription> findSubscriptionByIdAndTitleIgnoreCaseAndTariffIgnoreCaseAndDescriptionIgnoreCase(Long id, String title, String tariff, String description);
}
