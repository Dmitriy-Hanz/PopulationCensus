package populationcensus.service.interfaces;

import populationcensus.repository.entity.AccommodationsInfo;
import populationcensus.repository.entity.FullAddressInfo;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;

public interface PersonService {

    void savePerson(Person entity);
    void savePerson(Person entity, long householdId);
    void savePerson(Person entity, Household household);

    void updatePerson(Household entity);

    Person findPerson(long personId);
    Person findPersonByHousehold(Household household);
    Person findPersonByHouseholdId(long householdId);

    void deletePerson(Person person);
    void deletePersonById(long personId);
    void deletePersonByHousehold(Household household);
    void deletePersonByHouseholdId(long householdId);

    Household findLinkedHousehold(long personId);
    Household findLinkedHousehold(Person person);
}
