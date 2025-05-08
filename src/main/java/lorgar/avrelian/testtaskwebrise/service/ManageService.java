package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;
import lorgar.avrelian.testtaskwebrise.dto.UserDTO;

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

    /**
     * Method for adding user subscription to DB by ID
     *
     * @param user         {@link User} entity of user
     * @param subscription {@link SubscriptionNoUsers} entity of new user {@link Subscription}
     * @return {@link UserDTO} of saved {@link User}
     * @throws RuntimeException if no DB connection
     */
    boolean createUserSubscription(User user, SubscriptionNoUsers subscription);

    /**
     * Method for deleting {@link User} subscription from DB by ID of subscription
     *
     * @param user {@link User} entity of user
     * @param id   {@link Long} value of subscription ID
     * @return {@link UserDTO} of saved {@link User}
     * @throws RuntimeException if no DB connection
     */
    boolean deleteUserSubscription(User user, Long id);
}
