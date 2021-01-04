package com.tecso.exam.service;

import java.util.List;

public interface CrudService<T> {
    T save(T t);
    T update(T t);
    T findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
