package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.WorkInfo;

@Repository
public interface WorkInfoRep extends JpaRepository<WorkInfo, Long> {
}
