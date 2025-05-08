package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.NewUserDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserNoSubscriptions;
import lorgar.avrelian.testtaskwebrise.mapper.UserMapper;
import lorgar.avrelian.testtaskwebrise.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Tokovenko
 */
@Service
public class UsersServiceImpl implements UsersService {
    private final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public UsersServiceImpl(UsersRepository usersRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserNoSubscriptions> getAll() {
        List<User> users;
        try {
            users = usersRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if (users.isEmpty()) return new ArrayList<>();
        List<UserNoSubscriptions> result = new ArrayList<>();
        for (User user : users) {
            result.add(userMapper.userToUserNoSubscriptions(user));
        }
        return result;
    }

    @Override
    public List<UserNoSubscriptions> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<User> users;
        try {
            users = usersRepository.findAll(pageRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if (users.getContent().isEmpty()) return new ArrayList<>();
        List<UserNoSubscriptions> result = new ArrayList<>();
        for (User user : users.getContent()) {
            result.add(userMapper.userToUserNoSubscriptions(user));
        }
        return result;
    }

    @Override
    public UserNoSubscriptions createUser(NewUserDTO user) {
        List<User> byLogin;
        try {
            byLogin = usersRepository.findByLogin(user.getLogin());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if (!byLogin.isEmpty()) return null;
        UserNoSubscriptions userNoSubscriptions;
        try {
            userNoSubscriptions = userMapper.userToUserNoSubscriptions(usersRepository.save(userMapper.newUserDtoToUser(user)));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return userNoSubscriptions;
    }
}
