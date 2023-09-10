package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Estilo;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EstiloRepository implements Repository<Estilo, Long> {


    private Set<Estilo> estilos;

    private static volatile EstiloRepository instance;


    private EstiloRepository() {
        estilos = new LinkedHashSet<>();
    }

    /**
     * Padrão Singleton
     * @return
     */
    public static EstiloRepository of(){
        EstiloRepository result = instance;
        if(Objects.nonNull( result) ){
            return result;
        }

        synchronized (EstiloRepository.class){
            if(Objects.isNull( instance )){
                instance = new EstiloRepository();
            }
            return instance;
        }
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
