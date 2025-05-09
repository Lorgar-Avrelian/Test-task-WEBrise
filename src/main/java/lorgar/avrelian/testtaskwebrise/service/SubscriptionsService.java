package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dto.NewSubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionDTO;

import java.util.Collection;
import java.util.List;

/**
 * Service for managing subscriptions data
 *
 * @author Victor Tokovenko
 */
public interface SubscriptionsService {
    /**
     * Method for getting list of all subscriptions from DB
     *
     * @return {@link List} of {@link SubscriptionDTO} of all {@link Subscription}'s
     * @throws RuntimeException if no DB connection
     */
    List<SubscriptionDTO> getAll();

    /**
     * Method for getting pageable list of subscriptions from DB
     *
     * @param page page number
     * @param size page records count
     * @return paginal {@link List} of {@link SubscriptionDTO} of {@link Subscription}'s
     * @throws RuntimeException if no DB connection
     */
    List<SubscriptionDTO> getAll(int page, int size);

    /**
     * Method for creating new subscription in DB
     *
     * @param subscription {@link NewSubscriptionDTO} of the new subscription
     * @return {@link SubscriptionDTO} of created {@link Subscription}
     * @throws RuntimeException if no DB connection
     */
    SubscriptionDTO createSubscription(NewSubscriptionDTO subscription);

    /**
     * Method for getting subscription from DB by ID
     *
     * @param id {@link Long} value of subscription ID
     * @return {@link SubscriptionDTO} of {@link Subscription}
     * @throws RuntimeException if no DB connection
     */
    SubscriptionDTO readSubscription(Long id);

    /**
     * Method for updating subscription data at DB by ID
     *
     * @param subscription {@link NewSubscriptionDTO} of the new subscription
     * @param id           {@link Long} value of subscription ID
     * @return {@link SubscriptionDTO} of {@link Subscription}
     * @throws RuntimeException if no DB connection
     */
    SubscriptionDTO putSubscription(Long id, NewSubscriptionDTO subscription);

    /**
     * Method for deleting subscription from DB by ID
     *
     * @param id {@link Long} value of subscription ID
     * @return {@link SubscriptionDTO} of {@link Subscription}
     * @throws RuntimeException if no DB connection
     */
    SubscriptionDTO deleteSubscription(Long id);

    /**
     * Method for receiving the Subscription Top 3
     *
     * @return {@link Collection} of {@link SubscriptionDTO} entities of top 3 {@link Subscription}'s
     * @throws RuntimeException if no DB connection
     */
    Collection<SubscriptionDTO> readSubscriptionsTop();
}
