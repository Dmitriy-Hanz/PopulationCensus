package repository.dao.daos;

import repository.dao.daos.base.Dao;
import repository.dao.idaos.ChildrenInfoDaoImpl;
import repository.entity.ChildrenInfo;

import javax.persistence.EntityManager;

public class ChildrenInfoDao extends Dao<ChildrenInfo> implements ChildrenInfoDaoImpl {

    public ChildrenInfoDao(EntityManager em) {
        super(em, ChildrenInfo.class);
    }
}
