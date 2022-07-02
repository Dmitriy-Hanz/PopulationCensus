package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.LivingPlaceInfo;

@Repository
public interface LivingPlaceInfoRep extends JpaRepository<LivingPlaceInfo, Long> {
}
