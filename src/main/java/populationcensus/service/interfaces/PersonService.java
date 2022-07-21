package populationcensus.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.FullAddressInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;

import java.util.List;

public interface PersonService {

    void savePerson(Person entity);
    void savePerson(Person entity, long householdId);
    void savePerson(Person entity, Household household);

    List<Person> findAll();
    Page<Person> findAllAndPaginate(Pageable pageable);
    Person findPerson(long personId);
    Person findPersonByHousehold(Household household);
    Person findPersonByHouseholdId(long householdId);
    Person findPersonByPassportID(String passportID);

    void deletePerson(Person person);
    void deletePersonById(long personId);
    void deletePersonByHousehold(Household household);
    void deletePersonByHouseholdId(long householdId);

    Household findLinkedHousehold(long personId);
    Household findLinkedHousehold(Person person);
}
