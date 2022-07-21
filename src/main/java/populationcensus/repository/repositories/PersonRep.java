package populationcensus.repository.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRep extends JpaRepository<Person, Long> {
    Optional<Person> findByPassportID(String passportID);

    Optional<Person> findByLivingPlaceInfo(LivingPlaceInfo livingPlaceInfo);
    Optional<Person> findByLivingCountryInfo(LivingCountryInfo livingCountryInfo);
    Optional<Person> findByEducationInfo(EducationInfo educationInfo);
    Optional<Person> findByWorkInfo(WorkInfo workInfo);
    Optional<Person> findByChildrenInfo(ChildrenInfo childrenInfo);
}
