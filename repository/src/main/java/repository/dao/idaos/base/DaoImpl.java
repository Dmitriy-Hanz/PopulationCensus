package repository.dao.idaos.base;

import java.util.List;

public interface DaoImpl<T> {

    T selectById(Integer id);

    List<T> selectAll();

    void insert(T t);

    void insert(List<T> list);

    void update(T t);

    void delete(Integer id);

    void closeDao();
}
