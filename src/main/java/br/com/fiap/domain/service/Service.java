package br.com.fiap.domain.service;

import java.util.List;
import java.util.Objects;

public interface Service<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public List<T> findByName(String texto);

    public T persist(T t);

    default boolean valido(String s) {
        return Objects.nonNull( s ) && !s.trim().isEmpty();
    }

}