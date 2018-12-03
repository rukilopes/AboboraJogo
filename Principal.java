package com.example.rukilopes.projetofinal;

import android.os.Bundle;

import com.example.rukilopes.projetofinal.AndGraph.AGActivityGame;

public class Principal extends AGActivityGame {

    CenaAbertura abertura = null;
    CenaMenu menu = null;
    CenaConfiguracoes configuracoes = null;
    CenaCreditos creditos = null;
    Jogando jogando = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Dar a partida no motor
        this.init(this,false);//falso no emulador e verdadeiro no celular

        //Intacia o objeto de cena
        abertura = new CenaAbertura(getGameManager());
        menu = new CenaMenu(getGameManager());
        creditos = new CenaCreditos(getGameManager());
        configuracoes = new CenaConfiguracoes(getGameManager());
        jogando = new Jogando((getGameManager()));

        getGameManager().addScene(abertura);//0
        getGameManager().addScene(menu);//1
        getGameManager().addScene(configuracoes);//2
        getGameManager().addScene(creditos);//3
        getGameManager().addScene(jogando);//4
    }
}
