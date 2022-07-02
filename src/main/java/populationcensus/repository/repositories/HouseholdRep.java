package populationcensus.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import populationcensus.repository.entity.Household;

@Repository
public interface HouseholdRep extends JpaRepository<Household, Long> {
}
