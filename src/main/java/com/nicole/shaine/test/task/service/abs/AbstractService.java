package com.nicole.shaine.test.task.service.abs;

import java.util.List;
import java.util.Optional;

public interface AbstractService<PK, E> {

    List<E> getAll();
    void create(E entity);
    void update(E entity);
    void delete(E entity);
    void deleteById(PK id);
    Optional<E> getById(PK id);
    boolean isExistById(PK id);

}
