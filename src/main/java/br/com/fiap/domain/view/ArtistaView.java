package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.service.ArtistaService;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class ArtistaView implements View<Artista, Long> {

    private ArtistaService service;

    public ArtistaView() {
        service = new ArtistaService();
    }

    @Override
    public List<Artista> findAll() {
        return service.findAll();
    }

    @Override
    public Artista findById(Long id) {
        Long identificador = 0L;

        do {
            identificador = Long.valueOf( JOptionPane.showInputDialog( "Id do Artista" ) );
        } while (identificador <= 0L);

        return service.findById( identificador );
    }

    @Override
    public List<Artista> findByName(String texto) {
        String nome = service.valido( texto ) ? texto : JOptionPane.showInputDialog( "Nome do Artista" );
        while (!service.valido( nome )) {
            nome = JOptionPane.showInputDialog( "Nome do Artista" );
        }
        return service.findByName( nome );
    }

    @Override
    public Artista persist(Artista artista) {
        var valido = false;

        String nome = null;

        do {
            nome = JOptionPane
                    .showInputDialog( "Nome do Artista",
                            Objects.nonNull( artista ) &&
                                    Objects.nonNull( artista.getNome() ) ? artista.getNome() : "" );
        } while (!service.valido( nome ));

        Artista a = new Artista();
        a.setNome( nome );
        if (Objects.nonNull( artista ) && Objects.nonNull( artista.getId() )) a.setId( artista.getId() );
        return service.persist( a );
    }
}
