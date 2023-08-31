package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Musica;
import br.com.fiap.domain.repository.MusicaRepository;

import java.util.List;

public class MusicaService implements Service<Musica, Long>{

    private MusicaRepository repository;

    public MusicaService() {
        repository =  MusicaRepository.of();
    }

    @Override
    public List<Musica> findAll() {
        return repository.findAll();
    }

    @Override
    public Musica findById(Long id) {
        return repository.findById( id );
    }

    @Override
    public List<Musica> findByName(String texto) {
        return repository.findByName( texto );
    }

    @Override
    public Musica persist(Musica musica) {
        return repository.persist( musica );
    }
}
