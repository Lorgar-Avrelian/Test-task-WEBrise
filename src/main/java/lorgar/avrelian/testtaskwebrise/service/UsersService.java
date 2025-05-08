package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dto.UserNoSubscriptions;

import java.util.List;

/**
 * @author Victor Tokovenko
 */
public interface UsersService {
    /**
     * Method for getting list of all users from DB
     *
     * @return {@link List} of {@link UserNoSubscriptions} of all users
     */
    List<UserNoSubscriptions> getAll();

    /**
     * Method for getting pageable list of users from DB
     *
     * @return paginal {@link List} of {@link UserNoSubscriptions} of users
     */
    List<UserNoSubscriptions> getAll(int page, int size);
}
