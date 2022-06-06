package repository;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.dao.DaoFactory;
import repository.dao.idaos.LivingCountryInfoDaoImpl;
import repository.dao.idaos.PersonDaoImpl;
import repository.entity.LivingCountryInfo;
import repository.entity.Person;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LivingCountryInfoDaoTest {
    private PersonDaoImpl personDao;
    private LivingCountryInfoDaoImpl livingCountryInfoDao;
    private static Person person;
    private static LivingCountryInfo livingCountryInfo;

    @Before
    public void preset(){
        personDao = DaoFactory.getInstance().createPersonDao();
        livingCountryInfoDao = DaoFactory.getInstance().createLivingCountryInfoDao();
    }
    @After
    public void postset(){
        personDao.closeDao();
        livingCountryInfoDao.closeDao();
    }


    @Test
    public void t1_InsertTest()
    {
        livingCountryInfo = LivingCountryInfo.builder().didYouLiveInOtherCountry(true).build();
        person = Person.builder().isForeign(false).livingCountryInfo(livingCountryInfo).build();

        personDao.insert(person);

        Person personFromDB = personDao.selectById(person.getId());

        assertNotNull(personFromDB);
        assertNotNull(personFromDB.getLivingCountryInfo());
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(livingCountryInfo.getId(),personFromDB.getLivingCountryInfo().getId());
    }

    @Test
    public void t2_UpdateTest()
    {
        livingCountryInfo.setDidYouLiveInOtherCountry(false);
        livingCountryInfoDao.update(livingCountryInfo);

        Person personFromDB = personDao.selectById(person.getId());

        assertNotNull(personFromDB);
        assertNotNull(personFromDB.getLivingCountryInfo());
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(livingCountryInfo.getDidYouLiveInOtherCountry(),personFromDB.getLivingCountryInfo().getDidYouLiveInOtherCountry());
    }

    @Test
    public void t3_DeleteTest()
    {
        personDao.delete(person.getId());

        Person personFromDB = personDao.selectById(person.getId());
        LivingCountryInfo livingCountryInfoFromDB = livingCountryInfoDao.selectById(livingCountryInfo.getId());

        assertNull(personFromDB);
        assertNull(livingCountryInfoFromDB);
    }
}
