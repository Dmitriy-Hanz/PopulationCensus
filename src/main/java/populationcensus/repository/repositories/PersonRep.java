package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.Person;

@Repository
public interface PersonRep extends JpaRepository<Person, Long> {
}
