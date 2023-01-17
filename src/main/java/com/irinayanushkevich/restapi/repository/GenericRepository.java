package com.irinayanushkevich.restapi.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T create(T t);

    T update(T t);

    T getById(ID id);

    void delete(ID id);

    List<T> getAll();
}