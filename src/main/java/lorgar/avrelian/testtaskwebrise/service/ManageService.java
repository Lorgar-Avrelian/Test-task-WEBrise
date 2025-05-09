package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionDTO;

import java.util.Collection;
import java.util.Map;

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
     * @param subscription {@link SubscriptionDTO} entity of new user {@link Subscription}
     * @return {@link Boolean} flag of failed statement
     * @throws RuntimeException if no DB connection
     */
    boolean createUserSubscription(User user, SubscriptionDTO subscription);

    /**
     * Method for deleting {@link User} subscription from DB by ID of subscription
     *
     * @param user {@link User} entity of user
     * @param id   {@link Long} value of subscription ID
     * @return {@link Boolean} flag of failed statement
     * @throws RuntimeException if no DB connection
     */
    boolean deleteUserSubscription(User user, Long id);

    /**
     * Method for creating data records of subscription of current user in DB
     *
     * @param user   {@link User} entity of user
     * @param id     {@link Long} value of subscription ID
     * @param params {@link Map} of params keys and values
     * @return {@link Map} of new params values
     * @throws RuntimeException if no DB connection
     */
    Map<String, String> putUserSubscriptionData(User user, Long id, Map<String, String> params);

    /**
     * Method for getting data records of subscription of current user from DB
     *
     * @param user {@link User} entity of user
     * @param id   {@link Long} value of subscription ID
     * @return {@link Map} of new params values
     * @throws RuntimeException if no DB connection
     */
    Map<String, String> getUserSubscriptionData(User user, Long id);
}
