package repository.dao.daos;

import repository.dao.DaoException;
import repository.dao.daos.base.Dao;
import repository.dao.idaos.HouseholdDaoImpl;
import repository.entity.Household;

import javax.persistence.EntityManager;

public class HouseholdDao extends Dao<Household> implements HouseholdDaoImpl {

    public HouseholdDao(EntityManager em) {
        super(em, Household.class);
    }

    @Override
    public Household selectById(final Integer id) {
        Household entity = null;
        try {
            entity = entityManager.find(aClass, id);
            if(entity == null){
                return entity;
            }
            if(entity.getPersons() == null) {
                entityManager.refresh(entity);
            }
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
        return entity;
    }
}
