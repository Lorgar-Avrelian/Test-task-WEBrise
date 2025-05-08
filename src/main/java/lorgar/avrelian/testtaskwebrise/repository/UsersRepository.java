package lorgar.avrelian.testtaskwebrise.repository;

import lorgar.avrelian.testtaskwebrise.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Victor Tokovenko
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findByLogin(String login);
}
