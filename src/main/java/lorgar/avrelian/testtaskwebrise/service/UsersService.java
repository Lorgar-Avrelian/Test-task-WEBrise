package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.NewUserDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserNoSubscriptions;

import java.util.List;

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
     */
    List<UserNoSubscriptions> getAll();

    /**
     * Method for getting pageable list of users from DB
     *
     * @return paginal {@link List} of {@link UserNoSubscriptions} of {@link User}'s
     */
    List<UserNoSubscriptions> getAll(int page, int size);

    /**
     * Method for creating new user in DB
     *
     * @return {@link UserNoSubscriptions} of created {@link User}
     */
    UserNoSubscriptions createUser(NewUserDTO user);
}
