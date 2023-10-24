package com.nicole.shaine.test.task.service.abs;

import com.nicole.shaine.test.task.dao.abs.AbstractDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public abstract class AbstractServiceImpl<PK, E> implements AbstractService<PK, E> {

    protected final AbstractDao<PK, E> abstractDao;

    public AbstractServiceImpl(AbstractDao<PK, E> abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public List<E> getAll() {
        return abstractDao.getAll();
    }

    @Override
    @Transactional
    public void create(E entity) {
        abstractDao.create(entity);
    }

    @Override
    @Transactional
    public void update(E entity) {
        abstractDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(E entity) {
        abstractDao.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(PK id) {
        abstractDao.deleteById(id);
    }

    @Override
    public Optional<E> getById(PK id) {
        return abstractDao.getById(id);
    }

    @Override
    public boolean isExistById(PK id) {
        return abstractDao.isExistById(id);
    }
}
