package repository.dao.daos;

import repository.dao.daos.base.Dao;
import repository.dao.idaos.LivingCountryInfoDaoImpl;
import repository.entity.LivingCountryInfo;

import javax.persistence.EntityManager;

public class LivingCountryInfoDao extends Dao<LivingCountryInfo> implements LivingCountryInfoDaoImpl {

    public LivingCountryInfoDao(EntityManager em) {
        super(em, LivingCountryInfo.class);
    }
}
