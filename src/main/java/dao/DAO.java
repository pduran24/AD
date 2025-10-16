package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> save(T t);
    Optional<T> update(T t);
    Optional<T> delete(T t);

    List<T> findAll();
    Optional<T> findById(Integer id);

}
