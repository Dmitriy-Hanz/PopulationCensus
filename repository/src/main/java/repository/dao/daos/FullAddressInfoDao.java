package repository.dao.daos;

import repository.dao.daos.base.Dao;
import repository.dao.idaos.FullAddressInfoDaoImpl;
import repository.entity.FullAddressInfo;

import javax.persistence.EntityManager;

public class FullAddressInfoDao extends Dao<FullAddressInfo> implements FullAddressInfoDaoImpl {
    public FullAddressInfoDao(EntityManager em){
        super(em, FullAddressInfo.class);
    }
}
