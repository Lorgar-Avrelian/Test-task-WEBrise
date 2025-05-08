package lorgar.avrelian.testtaskwebrise.repository;

import lorgar.avrelian.testtaskwebrise.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Victor Tokovenko
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
}
