package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.EducationInfo;
import populationcensus.repository.entity.Person;

import java.util.Optional;

@Repository
public interface EducationInfoRep extends JpaRepository<EducationInfo, Long> {
    Optional<EducationInfo> findByPersonInEducationInfo(Person person);
    void deleteByPersonInEducationInfo(Person person);
}
