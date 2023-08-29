package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Musica;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MusicaRepository implements Repository<Musica, Long> {

    private Set<Musica> musicas;

    public MusicaRepository() {
        musicas = new LinkedHashSet<>();
    }

    @Override
    public List<Musica> findAll() {
        return musicas.stream().toList();
    }

    @Override
    public Musica findById(Long id) {
        return musicas.stream()
                .filter( m -> m.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }

    @Override
    public List<Musica> findByName(String texto) {
        return musicas.stream()
                .filter( m -> m.getNome().toLowerCase().contains( texto.toLowerCase() ) )
                .toList();
    }

    @Override
    public Musica persist(Musica musica) {
        if (Objects.isNull( musica )) return null;
        if (Objects.isNull( musica.getId() )) musica.setId( musicas.size() + 1L );
        musicas.add( musica );
        return musica;
    }
}
