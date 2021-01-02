package com.tecso.exam.service;

import java.util.List;

public interface CrudService<T> {
    T save(T t);
    T update(T t);
    T findById(String id);
    List<T> findAll();
    void deleteById(String id);
    boolean existsById(String id);
}
