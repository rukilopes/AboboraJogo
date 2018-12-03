package com.example.rukilopes.projetofinal;

import com.example.rukilopes.projetofinal.AndGraph.AGGameManager;
import com.example.rukilopes.projetofinal.AndGraph.AGInputManager;
import com.example.rukilopes.projetofinal.AndGraph.AGScene;
import com.example.rukilopes.projetofinal.AndGraph.AGScreenManager;
import com.example.rukilopes.projetofinal.AndGraph.AGSoundManager;
import com.example.rukilopes.projetofinal.AndGraph.AGSprite;
import com.example.rukilopes.projetofinal.AndGraph.AGTimer;

import static com.example.rukilopes.projetofinal.AndGraph.AGSoundManager.vrMusic;

public class CenaAbertura extends AGScene {

    AGTimer intervaloTempo = null;
    AGSprite Fundo = null;
    AGSprite botaoHelp = null;
    AGSprite botaoPlay = null;
    AGSprite botaoConfiguracao = null;
    int codigoSom = 0;

    public CenaAbertura(AGGameManager manager) {
        super(manager);//manager e quem gerencia as cenas
    }


    @Override
    public void init() {
        intervaloTempo = new AGTimer(3000);

       // AGSoundManager.vrMusic.loadMusic("music");
       // AGSoundManager.vrMusic.play();
        codigoSom = AGSoundManager.vrSoundEffects.loadSoundEffect("toc.wav");

        Fundo = createSprite(R.mipmap.bobora_fundo,1,1);

        Fundo.setScreenPercent(100,100);
        //coloque o centro dessa img em tal posicao
        //altura/2
        //largura/2
        Fundo.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        Fundo.vrPosition.setY(AGScreenManager.iScreenHeight/2);

        botaoHelp = createSprite(R.mipmap.icone_help,1,1);
        botaoHelp.setScreenPercent(35,13);

        botaoHelp.vrPosition.setX(980);
        botaoHelp.vrPosition.setY(150);

        botaoPlay = createSprite(R.mipmap.icone_play,1,1);
        botaoPlay.setScreenPercent(90,30);

        botaoPlay.vrPosition.setX(540);
        botaoPlay.vrPosition.setY(160);

        botaoConfiguracao = createSprite(R.mipmap.icone_configuracoes,1,1);
        botaoConfiguracao.setScreenPercent(35,13);

        botaoConfiguracao.vrPosition.setX(115);
        botaoConfiguracao.vrPosition.setY(150);

        //chamado toda vez que uma cena e apresentada
        setSceneBackgroundColor(0,1,1);//pinta a cor de fundo

        vrMusic.loadMusic("musica.mp3",true);
        vrMusic.play();
    }

    @Override
    public void restart() {
    //chamado na volta de uma interrupcao
    }

    @Override
    public void stop() {
        //Chamado quando a interrupcao ocorrer
    }

    @Override
    public void loop() {
        //Chamado N vezes por segundo que ira controlar a logica da cena
        //A logica da cena

        intervaloTempo.update();

        //0- Azul claro(abertura) / 1 - vermelho(menu)/ 2 - verde(configuracoes) / 3-Azul(creditos))
        //botaoHelp - help / botaoPlay - play / botaoConfiguracao - configuracao

        if(AGInputManager.vrTouchEvents.screenClicked()) {

            if (botaoHelp.collide(AGInputManager.vrTouchEvents.getLastPosition())) //verifica se a pessoa clicou no botao
            {
                AGSoundManager.vrSoundEffects.play(codigoSom);
                vrGameManager.setCurrentScene(3);//vai pra tela de creditos
                return;
            }

            if (botaoPlay.collide(AGInputManager.vrTouchEvents.getLastPosition())) //verifica se a pessoa clicou no botao
            {
                AGSoundManager.vrSoundEffects.play(codigoSom);
                vrGameManager.setCurrentScene(4);//vai pra tela de jogando
                return;
            }

            if (botaoConfiguracao.collide(AGInputManager.vrTouchEvents.getLastPosition())) //verifica se a pessoa clicou no botao
            {
                AGSoundManager.vrSoundEffects.play(codigoSom);
                vrGameManager.setCurrentScene(2);//vai pra tela de configuracao
                return;
            }


        }


        /*if(AGInputManager.vrTouchEvents.screenClicked() || intervaloTempo.isTimeEnded())
        {
            vrGameManager.setCurrentScene(1);
        }*/

        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            vrGameManager.vrActivity.finish();
        }
    }
}


