package com.example.rukilopes.projetofinal;

import com.example.rukilopes.projetofinal.AndGraph.AGGameManager;
import com.example.rukilopes.projetofinal.AndGraph.AGInputManager;
import com.example.rukilopes.projetofinal.AndGraph.AGScene;
import com.example.rukilopes.projetofinal.AndGraph.AGScreenManager;
import com.example.rukilopes.projetofinal.AndGraph.AGSprite;
import com.example.rukilopes.projetofinal.AndGraph.AGTimer;

public class CenaCreditos extends AGScene
{
    AGTimer intervaloTempo = null;
    AGSprite ImgSprite = null;
    AGSprite gatoSprite = null;

    public CenaCreditos(AGGameManager manager) {
        super(manager);//manager e quem gerencia as cenas
    }


    @Override
    public void init() {
        intervaloTempo = new AGTimer(3000);
        //chamado toda vez que uma cena e apresentada

        ImgSprite = createSprite(R.mipmap.tela_creditos,1,1);

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

        gatoSprite.addAnimation(8,true,0,3);

        setSceneBackgroundColor(0,0,1);//pinta a cor de fundo
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

        /*if(AGInputManager.vrTouchEvents.screenClicked() || intervaloTempo.isTimeEnded())
        {
            vrGameManager.setCurrentScene(0);

        }*/

        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            vrGameManager.setCurrentScene(0);
        }
    }
}
