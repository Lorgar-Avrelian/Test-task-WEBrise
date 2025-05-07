package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.model.User;
import lorgar.avrelian.testtaskwebrise.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Victor Tokovenko
 */
@Service
public class UsersServiceImpl implements UsersService {
    private final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAll() {
        List<User> all = usersRepository.findAll();
        log.debug("All users retrieved: " + all);
        return all;
    }

    @Override
    public User createUser(User user) {
        User create = usersRepository.save(user);
        log.debug("Created user : " + create);
        return create;
    }
}
