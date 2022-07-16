package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.Person;
import populationcensus.repository.entity.User;

@Repository
public interface UserRep extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
