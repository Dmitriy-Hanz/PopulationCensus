package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.LivingCountryInfo;

@Repository
public interface LivingCountryInfoRep extends JpaRepository<LivingCountryInfo, Long> {
}
