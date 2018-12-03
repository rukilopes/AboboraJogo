package com.example.rukilopes.projetofinal;

import com.example.rukilopes.projetofinal.AndGraph.AGGameManager;
import com.example.rukilopes.projetofinal.AndGraph.AGInputManager;
import com.example.rukilopes.projetofinal.AndGraph.AGScene;
import com.example.rukilopes.projetofinal.AndGraph.AGScreenManager;
import com.example.rukilopes.projetofinal.AndGraph.AGSoundManager;
import com.example.rukilopes.projetofinal.AndGraph.AGSprite;
import com.example.rukilopes.projetofinal.AndGraph.AGTimer;

public class CenaMenu extends AGScene
{
    AGTimer intervaloTempo = null;
    AGSprite ImgSprite = null;
    int codigoSom = 0;
    AGSprite gatoSprite = null;

    public CenaMenu(AGGameManager manager) {
        super(manager);//manager e quem gerencia as cenas
    }


    @Override
    public void init() {
        intervaloTempo = new AGTimer(5000);
        //chamado toda vez que uma cena e apresentada

        //codigoSom = AGSoundManager.vrSoundEffects.loadSoundEffect("toc.wav");

        ImgSprite = createSprite(R.mipmap.tela_menu,1,1);

        ImgSprite.setScreenPercent(140,100);
        //coloque o centro dessa img em tal posicao
        //altura/2
        //largura/2
        ImgSprite.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        ImgSprite.vrPosition.setY(AGScreenManager.iScreenHeight/2);

        gatoSprite = createSprite(R.mipmap.gato_sprite,4,3);

        gatoSprite.setScreenPercent(35,20);

        gatoSprite.vrPosition.setX(AGScreenManager.iScreenWidth/2);
        gatoSprite.vrPosition.setY(400);

        gatoSprite.addAnimation(8,true,4,7);

        setSceneBackgroundColor(1,0,0);//pinta a cor de fundo
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
        intervaloTempo.update();


        /*if(AGInputManager.vrTouchEvents.screenClicked())
        {
            vrGameManager.setCurrentScene(2);//1 - vermelho/ 2 -
        }

        if(intervaloTempo.isTimeEnded())
        {
            vrGameManager.setCurrentScene(3);
        }*/

        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            vrGameManager.setCurrentScene(0);
        }
    }
}
