package repository;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.dao.DaoFactory;
import repository.dao.idaos.LivingPlaceInfoDaoImpl;
import repository.dao.idaos.PersonDaoImpl;
import repository.entity.LivingPlaceInfo;
import repository.entity.Person;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LivingPlaceInfoDaoTest {
    private PersonDaoImpl personDao;
    private LivingPlaceInfoDaoImpl livingPlaceInfoDao;
    private static Person person;
    private static LivingPlaceInfo livingPlaceInfo;

    @Before
    public void preset(){
        personDao = DaoFactory.getInstance().createPersonDao();
        livingPlaceInfoDao = DaoFactory.getInstance().createLivingPlaceInfoDao();
    }
    @After
    public void postset(){
        personDao.closeDao();
        livingPlaceInfoDao.closeDao();
    }


    @Test
    public void t1_InsertTest()
    {
        livingPlaceInfo = LivingPlaceInfo.builder().nameOfPreviousCountry("Ыыы").build();
        person = Person.builder().isForeign(false).livingPlaceInfo(livingPlaceInfo).build();

        personDao.insert(person);

        Person personFromDB = personDao.selectById(person.getId());

        assertNotNull(personFromDB);
        assertNotNull(personFromDB.getLivingPlaceInfo());
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(livingPlaceInfo.getId(),personFromDB.getLivingPlaceInfo().getId());
    }

    @Test
    public void t2_UpdateTest()
    {
        livingPlaceInfo.setNameOfPreviousCountry("Aaa");
        livingPlaceInfoDao.update(livingPlaceInfo);

        Person personFromDB = personDao.selectById(person.getId());

        assertNotNull(personFromDB);
        assertNotNull(personFromDB.getLivingPlaceInfo());
        assertEquals(person.getId(),personFromDB.getId());
        assertEquals(livingPlaceInfo.getNameOfPreviousCountry(),personFromDB.getLivingPlaceInfo().getNameOfPreviousCountry());
    }

    @Test
    public void t3_DeleteTest()
    {
        personDao.delete(person.getId());

        Person personFromDB = personDao.selectById(person.getId());
        LivingPlaceInfo livingPlaceInfoFromDB = livingPlaceInfoDao.selectById(livingPlaceInfo.getId());

        assertNull(personFromDB);
        assertNull(livingPlaceInfoFromDB);
    }
}
