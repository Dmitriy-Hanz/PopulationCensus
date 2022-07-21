package populationcensus.repository.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRep extends JpaRepository<Person, Long> {
    Optional<Person> findByPassportID(String passportID);

//    List<Person> findAll(Pageable pageable);
}
