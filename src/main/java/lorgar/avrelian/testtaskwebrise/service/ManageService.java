package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.User;

import java.util.Collection;

/**
 * Service for managing subscriptions data of users
 *
 * @author Victor Tokovenko
 */
public interface ManageService {
    /**
     * Method for getting {@link User} {@link Subscription}'s
     *
     * @param user current {@link User}
     * @return {@link Collection} of {@link Subscription}'s
     * @throws RuntimeException if no DB connection
     */
    Collection<Subscription> getUserSubscriptions(User user);

    /**
     * Method for deleting subscriptions data of {@link User}
     *
     * @param user current user
     * @throws RuntimeException if no DB connection
     */
    void deleteUser(User user);

    /**
     * Method for deleting subscriptions data of {@link Subscription}
     *
     * @param subscription current subscription
     * @throws RuntimeException if no DB connection
     */
    void deleteSubscription(Subscription subscription);
}
