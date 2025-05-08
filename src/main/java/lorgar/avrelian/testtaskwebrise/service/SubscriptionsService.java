package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dto.NewSubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;

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
     * @return {@link List} of {@link SubscriptionNoUsers} of all {@link Subscription}'s
     * @throws RuntimeException if no DB connection
     */
    List<SubscriptionNoUsers> getAll();

    /**
     * Method for getting pageable list of subscriptions from DB
     *
     * @param page page number
     * @param size page records count
     * @return paginal {@link List} of {@link SubscriptionNoUsers} of {@link Subscription}'s
     * @throws RuntimeException if no DB connection
     */
    List<SubscriptionNoUsers> getAll(int page, int size);

    /**
     * Method for creating new subscription in DB
     *
     * @param subscription {@link NewSubscriptionDTO} of the new subscription
     * @return {@link SubscriptionNoUsers} of created {@link Subscription}
     * @throws RuntimeException if no DB connection
     */
    SubscriptionNoUsers createSubscription(NewSubscriptionDTO subscription);

    /**
     * Method for getting subscription from DB by ID
     *
     * @param id {@link Long} value of subscription ID
     * @return {@link SubscriptionNoUsers} of {@link Subscription}
     * @throws RuntimeException if no DB connection
     */
    SubscriptionNoUsers readSubscription(Long id);
}
