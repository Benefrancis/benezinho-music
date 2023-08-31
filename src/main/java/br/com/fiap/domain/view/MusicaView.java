package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.entity.Estilo;
import br.com.fiap.domain.entity.Musica;
import br.com.fiap.domain.service.ArtistaService;
import br.com.fiap.domain.service.EstiloService;
import br.com.fiap.domain.service.MusicaService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class MusicaView implements View<Musica, Long> {

    private MusicaService service;
    private EstiloService estiloService;
    private ArtistaService artistaService;

    public MusicaView() {
        service = new MusicaService();
        estiloService = new EstiloService();
        artistaService = new ArtistaService();
    }

    @Override
    public List<Musica> findAll() {
        return service.findAll();
    }

    @Override
    public Musica findById(Long id) {

        Long identificador = 0L;

        do {
            identificador = Long.valueOf( JOptionPane.showInputDialog( "Id da Música" ) );
        } while (identificador <= 0L);

        return service.findById( identificador );
    }

    @Override
    public List<Musica> findByName(String texto) {
        String nome = service.valido( texto ) ? texto : JOptionPane.showInputDialog( "Nome da música" );
        while (!service.valido( nome )) {
            nome = JOptionPane.showInputDialog( "Nome da música" );
        }
        return service.findByName( nome );
    }

    @Override
    public Musica persist(Musica musica) {


        String nome = null;


        do {
            nome = JOptionPane.showInputDialog( "Nome da Música" );
        } while (!service.valido( nome ));


        var estilos = estiloService.findAll();

        if(Objects.isNull( estilos ) || estilos.size() == 0) {
            JOptionPane.showMessageDialog(null, "Não temos estilos cadastrados. Primeira mente cadastre um estilo" );
            return null;
        }

        Estilo estilo = (Estilo) JOptionPane.showInputDialog(
                null,
                "Selecione o Estilo Musical",
                "Estilos Musicais",
                JOptionPane.QUESTION_MESSAGE,
                null,
                estilos.toArray(),
                estilos.get( 0 )
        );

        var artistas = artistaService.findAll();

        if(Objects.isNull( artistas ) || artistas.size() == 0) {
            JOptionPane.showMessageDialog(null, "Não temos artistas cadastrados. Primeira mente cadastre um artista" );
            return null;
        }

        List<Artista> listaDeArtistas = new Vector<>();

        int continua = 0;

        String msg = "Deseja Inluir Outro Artista? [0] SIM [1] NÃO";

        do {

            Artista artista = (Artista) JOptionPane.showInputDialog(
                    null,
                    "Selecione o Artista",
                    "Artistas",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    artistas.toArray(),
                    artistas.get( 0 )
            );

            listaDeArtistas.add( artista );

            continua = Integer.parseInt( JOptionPane.showInputDialog( msg, "0" ) );

        } while (continua == 0);

        Musica m = new Musica();
        m.setNome( nome ).setEstilo( estilo );

        for(Artista a: listaDeArtistas){
            m.addArtista( a );
        }

        return service.persist( m );
    }
}
