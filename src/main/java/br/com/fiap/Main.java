package br.com.fiap;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.entity.Estilo;
import br.com.fiap.domain.entity.Musica;
import br.com.fiap.domain.service.ArtistaService;
import br.com.fiap.domain.service.EstiloService;
import br.com.fiap.domain.service.MusicaService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        EstiloService estiloService = new EstiloService();
        ArtistaService artistaService = new ArtistaService();
        MusicaService musicaService = new MusicaService();

        Estilo estilo = new Estilo();
        estilo.setNome( "MPB" );

        Artista artista = new Artista();
        artista.setNome( "Djavan" );

        Musica musica = new Musica();
        musica.setEstilo( estilo )
                .setNome( "Flor de Lis" )
                .addArtista( artista );

        estiloService.persist( estilo );
        artistaService.persist( artista );
        musicaService.persist( musica );

        musicaService.findAll().forEach( System.out::println );

        Long id = Long.valueOf( JOptionPane.showInputDialog( "ID da Musica" ) );

        Musica byId = musicaService.findById( id );

        System.out.println( byId );

        String nome = null;
        do {
            nome = JOptionPane.showInputDialog( "Nome da Musica" );
        } while (!musicaService.valido( nome ));

        musicaService.findByName( nome ).forEach( System.out::println );


    }
}