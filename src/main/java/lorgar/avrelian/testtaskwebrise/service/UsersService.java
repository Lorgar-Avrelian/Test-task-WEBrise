package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.NewUserDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserDTO;
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
     * Method for getting user from DB by ID
     *
     * @param user {@link NewUserDTO} of a new user
     * @param id   {@link Long} value of user ID
     * @return {@link UserDTO} of {@link User}
     * @throws RuntimeException if no DB connection
     */
    UserDTO putUser(Long id, NewUserDTO user);
}
