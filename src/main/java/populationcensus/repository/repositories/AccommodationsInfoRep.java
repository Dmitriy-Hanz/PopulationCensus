package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.AccommodationsInfo;

@Repository
public interface AccommodationsInfoRep extends JpaRepository<AccommodationsInfo,Long> {
}
