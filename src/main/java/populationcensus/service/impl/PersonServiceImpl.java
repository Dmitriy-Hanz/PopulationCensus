package populationcensus.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import populationcensus.repository.entity.Household;
import populationcensus.repository.entity.Person;
import populationcensus.repository.repositories.PersonRep;
import populationcensus.service.interfaces.PersonService;

import java.util.Collections;
import java.util.List;

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
    public List<Person> findAll() {
        return personRep.findAll();
    }

    @Override
    public Page<Person> findAllAndPaginate(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Person> list;
        List<Person> persons = personRep.findAll();

        if (persons.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, persons.size());
            list = persons.subList(startItem, toIndex);
        }

        Page<Person> personPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), persons.size());

        return personPage;
    }

    @Override
    public Person findPerson(long personId) {
        return personRep.findById(personId).orElse(null);
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
