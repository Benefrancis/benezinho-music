package br.com.fiap;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.entity.Estilo;
import br.com.fiap.domain.entity.Musica;
import br.com.fiap.domain.service.ArtistaService;
import br.com.fiap.domain.service.EstiloService;
import br.com.fiap.domain.service.MusicaService;
import br.com.fiap.domain.view.MenuView;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        MenuView menu = new MenuView();
        menu.show();


    }
}