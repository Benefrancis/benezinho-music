package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Estilo;
import br.com.fiap.domain.service.EstiloService;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class EstiloView implements View<Estilo, Long> {

    private EstiloService service;


    public EstiloView() {
        service = new EstiloService();
    }

    @Override
    public List<Estilo> findAll() {
        return service.findAll();
    }

    @Override
    public Estilo findById(Long id) {

        Long identificador = 0L;

        do {
            identificador = Long.valueOf( JOptionPane.showInputDialog( "Id do Estilo" ) );
        } while (identificador <= 0L);

        return service.findById( identificador );
    }

    @Override
    public List<Estilo> findByName(String texto) {
        String nome = service.valido( texto ) ? texto : JOptionPane.showInputDialog( "Nome do Estilo" );
        while (!service.valido( nome )) {
            nome = JOptionPane.showInputDialog( "Nome do Estilo" );
        }
        return service.findByName( nome );
    }

    @Override
    public Estilo persist(Estilo estilo) {
        var valido = false;
        String nome = null;

        do {
            nome = JOptionPane
                    .showInputDialog( "Nome do estilo Musical",
                            Objects.nonNull( estilo ) &&
                                    Objects.nonNull( estilo.getNome() ) ? estilo.getNome() : "" );
        } while (!service.valido( nome ));

        Estilo e = new Estilo();
        e.setNome( nome );
        if (Objects.nonNull( estilo ) && Objects.nonNull( estilo.getId() ))  e.setId( estilo.getId() );
        return service.persist( e );
    }
}
