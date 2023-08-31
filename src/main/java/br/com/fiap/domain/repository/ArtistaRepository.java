package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Artista;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ArtistaRepository implements Repository<Artista, Long> {

    private Set<Artista> artistas;

    private static volatile ArtistaRepository instance;

    private ArtistaRepository() {
        artistas = new LinkedHashSet<>();
    }

    /**
     * Padrão Singleton
     * @return
     */
    public static ArtistaRepository of(){
        ArtistaRepository result = instance;
        if(Objects.nonNull( result) ){
            return result;
        }

        synchronized (ArtistaRepository.class){
            if(Objects.isNull( null )){
                instance = new ArtistaRepository();
            }
            return instance;
        }
    }

    @Override
    public List<Artista> findAll() {
        return artistas.stream().toList();
    }

    @Override
    public Artista findById(Long id) {
        return artistas.stream()
                .filter( a -> a.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }

    @Override
    public List<Artista> findByName(String texto) {
        return artistas.stream()
                .filter( a -> a.getNome().toLowerCase().contains( texto.toLowerCase() ) )
                .toList();
    }

    @Override
    public Artista persist(Artista artista) {
        if(Objects.isNull( artista )) return null;
        if(Objects.isNull( artista.getId() )) artista.setId( artistas.size() + 1L );
        artistas.add( artista );
        return artista;
    }
}
