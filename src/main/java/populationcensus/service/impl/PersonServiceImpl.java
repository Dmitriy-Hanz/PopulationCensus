package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.interfaces.PersonService;

@Service("personService")
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRep personRep;

    @Override
    public void savePerson(Person entity) {

    }

    @Override
    public void savePerson(Person entity, long householdId) {

    }

    @Override
    public void savePerson(Person entity, Household household) {

    }

    @Override
    public void updatePerson(Household entity) {

    }

    @Override
    public Person findPerson(long personId) {
        return null;
    }

    @Override
    public Person findPersonByHousehold(Household household) {
        return null;
    }

    @Override
    public Person findPersonByHouseholdId(long householdId) {
        return null;
    }

    @Override
    public Person findPersonByPassportID(String passportID) {
        return personRep.findByPassportID(passportID).orElse(null);
    }

    @Override
    public void deletePerson(Person person) {

    }

    @Override
    public void deletePersonById(long personId) {

    }

    @Override
    public void deletePersonByHousehold(Household household) {

    }

    @Override
    public void deletePersonByHouseholdId(long householdId) {

    }

    @Override
    public Household findLinkedHousehold(long personId) {
        return null;
    }

    @Override
    public Household findLinkedHousehold(Person person) {
        return null;
    }
}
