package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;

import java.util.List;

/**
 * @author Victor Tokovenko
 */
public interface SubscriptionsService {
    /**
     * Method for getting list of all subscriptions from DB
     *
     * @return {@link List} of {@link SubscriptionNoUsers} of all subscriptions
     */
    List<SubscriptionNoUsers> getAll();

    /**
     * Method for getting pageable list of subscriptions from DB
     *
     * @return paginal {@link List} of {@link SubscriptionNoUsers} of subscriptions
     */
    List<SubscriptionNoUsers> getAll(int page, int size);
}
