package lorgar.avrelian.testtaskwebrise.repository;

import lorgar.avrelian.testtaskwebrise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Victor Tokovenko
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
