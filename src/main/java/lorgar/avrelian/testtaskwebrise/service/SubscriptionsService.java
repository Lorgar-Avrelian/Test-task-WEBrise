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
     */
    List<SubscriptionNoUsers> getAll();

    /**
     * Method for getting pageable list of subscriptions from DB
     *
     * @return paginal {@link List} of {@link SubscriptionNoUsers} of {@link Subscription}'s
     */
    List<SubscriptionNoUsers> getAll(int page, int size);

    /**
     * Method for creating new subscription in DB
     *
     * @return {@link SubscriptionNoUsers} of created {@link Subscription}
     */
    SubscriptionNoUsers createSubscription(NewSubscriptionDTO subscription);
}
