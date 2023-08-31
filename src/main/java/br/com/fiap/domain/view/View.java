package br.com.fiap.domain.view;

import java.util.List;

public interface View<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public List<T> findByName(String texto);

    public T persist(T t);
}
