package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.LivingCountryInfo;
import populationcensus.repository.entity.Person;

import java.util.Optional;

@Repository
public interface LivingCountryInfoRep extends JpaRepository<LivingCountryInfo, Long> {
    Optional<LivingCountryInfo> findByPersonInLivingCountryInfo(Person person);
    void deleteByPersonInLivingCountryInfo(Person person);
}
