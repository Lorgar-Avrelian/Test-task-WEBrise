package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.User;

import java.util.List;

/**
 * @author Victor Tokovenko
 */
public interface UsersService {
    List<User> getAll();

    User createUser(User user);
}
