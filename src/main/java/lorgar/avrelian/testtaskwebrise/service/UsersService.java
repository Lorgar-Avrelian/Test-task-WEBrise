package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.NewUserDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserNoSubscriptions;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Service for managing users data
 *
 * @author Victor Tokovenko
 */
public interface UsersService {
    /**
     * Method for getting list of all users from DB
     *
     * @return {@link List} of {@link UserNoSubscriptions} of all {@link User}'s
     * @throws RuntimeException if no DB connection
     */
    List<UserNoSubscriptions> getAll();

    /**
     * Method for getting pageable list of users from DB
     *
     * @param page page number
     * @param size page records count
     * @return paginal {@link List} of {@link UserNoSubscriptions} of {@link User}'s
     * @throws RuntimeException if no DB connection
     */
    List<UserNoSubscriptions> getAll(int page, int size);

    /**
     * Method for creating new user in DB
     *
     * @param user {@link NewUserDTO} of a new user
     * @return {@link UserNoSubscriptions} of created {@link User}
     * @throws RuntimeException if no DB connection
     */
    UserNoSubscriptions createUser(NewUserDTO user);

    /**
     * Method for getting user from DB by ID
     *
     * @param id {@link Long} value of user ID
     * @return {@link UserDTO} of {@link User}
     * @throws RuntimeException if no DB connection
     */
    UserDTO readUser(Long id);

    /**
     * Method for updating user data at DB by ID
     *
     * @param user {@link NewUserDTO} of a new user
     * @param id   {@link Long} value of user ID
     * @return {@link UserDTO} of {@link User}
     * @throws RuntimeException if no DB connection
     */
    UserDTO putUser(Long id, NewUserDTO user);

    /**
     * Method for deleting user from DB by ID
     *
     * @param id {@link Long} value of user ID
     * @return {@link UserDTO} of {@link User}
     * @throws RuntimeException if no DB connection
     */
    UserDTO deleteUser(Long id);

    /**
     * Method for getting user subscriptions from DB by ID
     *
     * @param id {@link Long} value of user ID
     * @return {@link Collection} of {@link SubscriptionDTO} of found {@link Subscription}
     * @throws RuntimeException if no DB connection
     */
    Collection<SubscriptionDTO> readUserSubscriptions(Long id);

    /**
     * Method for adding user subscription to DB by ID
     *
     * @param id           {@link Long} value of user ID
     * @param subscription {@link SubscriptionDTO} entity of new user {@link Subscription}
     * @return {@link UserDTO} of saved {@link User}
     * @throws RuntimeException if no DB connection
     */
    UserDTO createUserSubscription(Long id, SubscriptionDTO subscription);

    /**
     * Method for deleting user subscription from DB by ID's of user and subscription
     *
     * @param userId {@link Long} value of user ID
     * @param subId  {@link Long} value of subscription ID
     * @return {@link UserDTO} of saved {@link User}
     * @throws RuntimeException if no DB connection
     */
    UserDTO deleteUserSubscription(Long userId, Long subId);

    /**
     * Method for creating data records of subscription of current user in DB
     *
     * @param userId {@link Long} value of {@link User} ID
     * @param subID  {@link Long} value of {@link Subscription} ID
     * @param params {@link Map} of params keys and values
     * @return {@link Map} of current user subscription params values
     * @throws RuntimeException if no DB connection
     */
    Map<String, String> putUserSubscriptionData(Long userId, Long subID, Map<String, String> params);

    /**
     * Method for getting data records of subscription of current user from DB
     *
     * @param userId {@link Long} value of {@link User} ID
     * @param subID  {@link Long} value of {@link Subscription} ID
     * @return {@link Map} of current user subscription params values
     * @throws RuntimeException if no DB connection
     */
    Map<String, String> getUserSubscriptionData(Long userId, Long subID);
}
