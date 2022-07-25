package populationcensus.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();
    Page<Person> findAllAndPaginate(Pageable pageable);
    Person findPerson(long personId);
    Person findPersonByPassportID(String passportID);

}
