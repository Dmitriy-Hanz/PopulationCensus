package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.FullAddressInfo;

@Repository
public interface FullAddressInfoRep extends JpaRepository<FullAddressInfo,Long> {

}
