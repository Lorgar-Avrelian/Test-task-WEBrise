package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.User;

import java.util.Collection;

/**
 * Service for managing users subscriptions data
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
}
