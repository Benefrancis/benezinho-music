package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.repository.ArtistaRepository;

import java.util.List;

public class ArtistaService implements Service<Artista, Long>{

    private ArtistaRepository repository;

    public ArtistaService() {
        repository = new ArtistaRepository();
    }

    @Override
    public List<Artista> findAll() {
        return repository.findAll();
    }

    @Override
    public Artista findById(Long id) {
        return repository.findById( id );
    }

    @Override
    public List<Artista> findByName(String texto) {
        return repository.findByName( texto );
    }

    @Override
    public Artista persist(Artista artista) {
        return repository.persist( artista );
    }
}
