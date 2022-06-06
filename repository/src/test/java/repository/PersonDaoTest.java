package repository;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.dao.DaoFactory;
import repository.dao.idaos.PersonDaoImpl;
import repository.entity.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDaoTest {
    private PersonDaoImpl personDao;
    private static Person person;
    private static Person person2;

    @Before
    public void preset(){
        personDao = DaoFactory.getInstance().createPersonDao();
    }
    @After
    public void postset(){
        personDao.closeDao();
    }

    @Test
    public void t1_InsertTest()
    {
        person = Person.builder().isForeign(true).build();
        personDao.insert(person);

        Person personFromDB = personDao.selectById(person.getId());
        assertNotNull(personFromDB);
        assertEquals(person.getId(),personFromDB.getId());
    }

    @Test
    public void t2_UpdateTest()
    {
        person.setAge(20);
        personDao.update(person);

        Person personFromDB = personDao.selectById(person.getId());
        assertNotNull(personFromDB);
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(person.getAge(),personFromDB.getAge());
    }

    @Test
    public void t3_DeleteTest()
    {
        personDao.delete(person.getId());

        Person personFromDB = personDao.selectById(person.getId());
        assertNull(personFromDB);
    }

    @Test
    public void t4_InsertGroupTest()
    {
        List<Person> persons = new ArrayList<>();
        person = Person.builder().isForeign(true).build();
        person2 = Person.builder().isForeign(false).build();
        persons.add(person);
        persons.add(person2);

        personDao.insert(persons);

        Person person1FromDB = personDao.selectById(person.getId());
        Person person2FromDB = personDao.selectById(person2.getId());

        assertNotNull(person1FromDB);
        assertNotNull(person2FromDB);
        assertNotEquals(person1FromDB,person2FromDB);
        assertEquals(person.getId(),person1FromDB.getId());
        assertEquals(person2.getId(),person2FromDB.getId());
    }

    @Test
    public void t5_SelectAllTest()
    {
        List<Person> personsFromDB = personDao.selectAll();
        assertNotNull(personsFromDB);
        assertEquals(2,personsFromDB.size());
        assertEquals(person.getId(),personsFromDB.get(0).getId());
        assertEquals(person2.getId(),personsFromDB.get(1).getId());
    }
}
