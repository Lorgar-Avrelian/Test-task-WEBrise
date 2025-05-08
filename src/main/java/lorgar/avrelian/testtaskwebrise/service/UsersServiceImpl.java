package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.NewUserDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserDTO;
import lorgar.avrelian.testtaskwebrise.dto.UserNoSubscriptions;
import lorgar.avrelian.testtaskwebrise.mapper.UserMapper;
import lorgar.avrelian.testtaskwebrise.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Victor Tokovenko
 */
@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    private final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final ManageService manageService;

    public UsersServiceImpl(
            UsersRepository usersRepository,
            UserMapper userMapper,
            @Qualifier(value = "manageServiceImpl") ManageService manageService
    ) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.manageService = manageService;
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
        User userByLogin;
        try {
            userByLogin = usersRepository.findUserByLogin(user.getLogin()).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if (userByLogin != null) return null;
        UserNoSubscriptions userNoSubscriptions;
        try {
            userNoSubscriptions = userMapper.userToUserNoSubscriptions(usersRepository.save(userMapper.newUserDtoToUser(user)));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return userNoSubscriptions;
    }

    @Override
    public UserDTO readUser(Long id) {
        User user = usersRepository.findById(id).orElse(null);
        if (user == null) return null;
        UserDTO userDTO;
        try {
            userDTO = userMapper.userToUserDTO(user, manageService.getUserSubscriptions(user));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return userDTO;
    }

    @Override
    public UserDTO putUser(Long id, NewUserDTO user) {
        if (readUser(id) == null) return null;
        User currentUser = usersRepository.findById(id).get();
        currentUser.setLogin(user.getLogin());
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        User savedUser;
        try {
            savedUser = usersRepository.save(currentUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return userMapper.userToUserDTO(savedUser, manageService.getUserSubscriptions(currentUser));
    }
}
