package repository.dao.daos.base;

import repository.dao.DaoException;
import repository.dao.idaos.base.DaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class Dao<T> implements DaoImpl<T> {

    protected final EntityManager entityManager;
    protected final Class<T> aClass;

    public Dao(final EntityManager em,
                         final Class<T> a) {
        this.entityManager = em;
        this.aClass = a;
    }

    @Override
    public T selectById(final Integer id) {
        T entity = null;
        try {
            entity = entityManager.find(aClass, id);
        } catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
        return entity;
    }

    @Override
    public List<T> selectAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(aClass);
        Root<T> rootEntry = criteriaQuery.from(aClass);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void insert(final T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new DaoException(e);
        }
    }

    @Override
    public void insert(final List<T> list) {
        try {
            entityManager.getTransaction().begin();
            list.forEach(entityManager::persist);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException(e);
        }
    }

    @Override
    public void update(final T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(final Integer id) {
        try {
            T entity = entityManager.find(aClass, id);
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new DaoException(e);
        }
    }

    @Override
    public void closeDao() {
        entityManager.close();
    }
}
