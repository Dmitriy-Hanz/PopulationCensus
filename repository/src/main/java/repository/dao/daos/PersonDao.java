package repository.dao.daos;

import repository.dao.DaoException;
import repository.dao.daos.base.Dao;
import repository.dao.idaos.PersonDaoImpl;
import repository.entity.Person;

import javax.persistence.EntityManager;

public class PersonDao extends Dao<Person> implements PersonDaoImpl {

    public PersonDao(EntityManager em) {
        super(em, Person.class);
    }
}
