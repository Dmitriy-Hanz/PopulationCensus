package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.ChildrenInfo;

@Repository
public interface ChildrenInfoRep extends JpaRepository<ChildrenInfo, Long> {
}
