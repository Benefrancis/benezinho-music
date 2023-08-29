package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Estilo;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EstiloRepository implements Repository<Estilo, Long> {

    private Set<Estilo> estilos;

    public EstiloRepository() {
        estilos = new LinkedHashSet<>();
    }

    @Override
    public List<Estilo> findAll() {
        return estilos.stream().toList();
    }

    @Override
    public Estilo findById(Long id) {
        return estilos.stream().filter( e -> e.getId().equals( id ) ).findFirst().orElse( null );
    }

    @Override
    public List<Estilo> findByName(String texto) {
        return estilos.stream().filter( e -> e.getNome().toLowerCase().contains( texto.toLowerCase() ) ).toList();
    }

    @Override
    public Estilo persist(Estilo estilo) {
        if (Objects.isNull( estilo )) return null;
        if (Objects.isNull( estilo.getId() )) estilo.setId( estilos.size() + 1L );
        estilos.add( estilo );
        return estilo;
    }
}
