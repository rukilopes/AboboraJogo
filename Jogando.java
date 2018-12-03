package com.example.rukilopes.projetofinal;

import com.example.rukilopes.projetofinal.AndGraph.AGActivityGame;
import com.example.rukilopes.projetofinal.AndGraph.AGGameManager;
import com.example.rukilopes.projetofinal.AndGraph.AGInputManager;
import com.example.rukilopes.projetofinal.AndGraph.AGScene;
import com.example.rukilopes.projetofinal.AndGraph.AGScreenManager;
import com.example.rukilopes.projetofinal.AndGraph.AGSprite;
import com.example.rukilopes.projetofinal.AndGraph.AGTimer;
import com.example.rukilopes.projetofinal.AndGraph.AGVector2D;

import java.util.ArrayList;
import java.util.Random;

public class Jogando extends AGScene {

    AGTimer intervaloTempo = null;
    AGTimer intervaloCaveira = null;

    AGSprite bordas = null;
    AGSprite fundoCinza = null;
    AGSprite botaoCima = null;
    AGSprite botaoBaixo = null;
    AGSprite botaoEsquerda = null;
    AGSprite botaoDireita = null;
    ArrayList<AGSprite> obstaculo = null;

    AGSprite aboboraFrente = null;
    AGSprite caveiraFrente1 = null;
    AGSprite caveiraFrente2 = null;
    AGSprite caveiraFrente3 = null;
    AGSprite obst = null;

    AGSprite doce = null;
    int pontuacaoTotal = 0;
    ArrayList posicoesDoceX = null;
    ArrayList posicoesDoceY = null;


    int posicaoCaveira1X = 100;
    int posicaoCaveira1Y = 1450;

    int posicaoCaveira2X = 1020;
    int posicaoCaveira2Y = 1450;

    int posicaoCaveira3X = 1020;
    int posicaoCaveira3Y = 500;

    int posicaoBoboraX = 0;
    int posicaoBoboraY = 0;


    public Jogando(AGGameManager manager) {
        super(manager);//manager e quem gerencia as cenas
    }

    public boolean verificaColisaoObstaculo(AGSprite obj, int direcao) {
        for (int i = 0; i < 9; i++) {

            if (obj.collide(obstaculo.get(i))) {
                if (direcao == 1) {//cima
                    if (obj.collideDireita(obstaculo.get(i)) == true)
                        return true;

                } else if (direcao == 2) {//baixo
                    if (obj.collideEsquerda(obstaculo.get(i)) == true)
                        return true;

                } else if (direcao == 3) {//direita
                    if (obj.collideFrente(obstaculo.get(i)) == true)
                        return true;

                } else if (direcao == 4) {//esquerda
                    if (obj.collideTras(obstaculo.get(i)) == true)
                        return true;
                }

            }
        }

        return false;
    }

    public boolean verificaColisaoDoce() {
        if (aboboraFrente.collide(doce)) {
            pontuacaoTotal += 5;
            //mostra '+5' na tela
            //atualiza pontuacao na tela
            return true;
        }
        return false;
    }

    public void alteraPosDoceRandom()
    {
        Random r = new Random();
        int indice = r.nextInt(15);
        doce.vrPosition.setXY((float)posicoesDoceX.get(indice),(float)posicoesDoceY.get(indice));
    }

    public void persegue(AGSprite caveira)
    {
        if(!caveira.collide(aboboraFrente)){

            if(caveira.vrPosition.getX() < aboboraFrente.vrPosition.getX())
                caveira.vrPosition.setX(caveira.vrPosition.getX()+30);
            else if(caveira.vrPosition.getX() > aboboraFrente.vrPosition.getX())
                caveira.vrPosition.setX(caveira.vrPosition.getX()-30);
            if(caveira.vrPosition.getY() < aboboraFrente.vrPosition.getY())
                caveira.vrPosition.setY(caveira.vrPosition.getY()+30);
            else if(caveira.vrPosition.getY() > aboboraFrente.vrPosition.getY())
                caveira.vrPosition.setY(caveira.vrPosition.getY()-30);
        }

        //else{vrGameManager.setCurrentScene(0);}//Cena de game over

    }


        @Override
        public void init() {
            intervaloTempo = new AGTimer(10000);
            intervaloCaveira = new AGTimer(700);

            obstaculo = new ArrayList<AGSprite>();

            posicoesDoceX = new ArrayList();
            posicoesDoceY = new ArrayList();
            float posX = ((AGScreenManager.iScreenWidth/100) *8);//8
            float posY = ((AGScreenManager.iScreenHeight/100) *26);//26
            posicoesDoceX.add(posX);
            posicoesDoceY.add(posY);

            for(int i=1;i < 16;i++){


                if(i == 4 || i == 8 || i==12)
                { posY = ((AGScreenManager.iScreenHeight/100) *26); posX +=310;}
                else
                    posY+= 310;

                posicoesDoceX.add(posX);
                posicoesDoceY.add(posY);
            }

            //Width - Largura
            //Height - Altura

            fundoCinza = createSprite(R.mipmap.fundao,1,1);
            fundoCinza.setScreenPercent(190,58);
            fundoCinza.vrPosition.setX(AGScreenManager.iScreenWidth/2);
            fundoCinza.vrPosition.setY(AGScreenManager.iScreenHeight/2);


            bordas = createSprite(R.mipmap.bordas,1,1);
            bordas.setScreenPercent(190,80);
            bordas.vrPosition.setX((AGScreenManager.iScreenWidth/100)*40);
            bordas.vrPosition.setY(AGScreenManager.iScreenHeight/2);//centro

            botaoCima = createSprite(R.mipmap.botao_cima,1,1);
            botaoCima.setScreenPercent(30,30);
            botaoCima.vrPosition.setX(400);
            botaoCima.vrPosition.setY(200);

            botaoBaixo = createSprite(R.mipmap.botao_baixo,1,1);
            botaoBaixo.setScreenPercent(30,25);
            botaoBaixo.vrPosition.setX(700);
            botaoBaixo.vrPosition.setY(200);

            botaoDireita = createSprite(R.mipmap.botao_direita,1,1);
            botaoDireita.setScreenPercent(30,30);
            botaoDireita.vrPosition.setX(400);
            botaoDireita.vrPosition.setY(1730);


            botaoEsquerda = createSprite(R.mipmap.botao_esquerda,1,1);
            botaoEsquerda.setScreenPercent(30,30);
            botaoEsquerda.vrPosition.setX(700);
            botaoEsquerda.vrPosition.setY(1730);


            botaoEsquerda = createSprite(R.mipmap.botao_esquerda,1,1);
            botaoEsquerda.setScreenPercent(30,30);
            botaoEsquerda.vrPosition.setX(700);   // AGScreenManager.iScreenHeight
            botaoEsquerda.vrPosition.setY(1730); //usar tamanho da tela ao inves de valor fixo


             //Width - Largura (x)   // Height - Altura (y)

             //(AGScreenManager.iScreenHeight / 100) * numero(exemplo: 70, no lugar de botar o valor fixo 700)

             for(int i=0;i<9;i++) {
                 obst = createSprite(R.mipmap.obstaculo, 1, 1);
                 obst.setScreenPercent(15, 9);

                 obst.vrPosition.setX((AGScreenManager.iScreenWidth / 100));
                 obst.vrPosition.setY(((AGScreenManager.iScreenHeight / 100)));//la em baixo pega esse result e reduz 15, errado

                 //seta as posicoes de Y dos obstaculos
                 if (i == 0 || i == 3 || i == 6) {
                     obst.vrPosition.setY((obst.vrPosition.getY() * 67));//baixo y--
                }else if(i==1 || i == 4 || i == 7) {obst.vrPosition.setY(obst.vrPosition.getY()*50);
                 }else if(i==2 || i==5 || i== 8){obst.vrPosition.setY(obst.vrPosition.getY()*33);}

                 //seta as posicoes de X dos obstaculos
                    if((i >= 0) && (i < 3)) {//0 1 2
                        obst.vrPosition.setX(obst.vrPosition.getX()*23);
                    }else if ((i > 2) && (i < 6))//3 4 5
                     {  obst.vrPosition.setX((obst.vrPosition.getX() * 55));
                     } else if (i > 5 && i < 9) {//6 7 8
                         obst.vrPosition.setX(obst.vrPosition.getX() * 85);}

                     obstaculo.add(obst);//adiciona o objeto no Arraylist de Obstaculo
                 }//fim do for

                 doce = createSprite(R.mipmap.doce, 1, 1);
                 doce.setScreenPercent(10, 8);
                 doce.vrPosition.setX((AGScreenManager.iScreenWidth/100)*70);
                 doce.vrPosition.setY((AGScreenManager.iScreenHeight/100)*59);


                 posicaoBoboraX = (AGScreenManager.iScreenWidth / 100) * 8;
                 posicaoBoboraY = (AGScreenManager.iScreenHeight / 100) * 25;

                 aboboraFrente = createSprite(R.mipmap.abobora_frente, 1, 1);
                 aboboraFrente.setScreenPercent(10, 5); //faça uma nova imagem...
                 aboboraFrente.vrPosition.setX(posicaoBoboraX);
                 aboboraFrente.vrPosition.setY(posicaoBoboraY);

                 caveiraFrente1 = createSprite(R.mipmap.caveira_frente, 1, 1);
                 caveiraFrente1.setScreenPercent(15, 10); // nova imagem pra caveira tmb
                 caveiraFrente1.vrPosition.setX(posicaoCaveira1X);
                 caveiraFrente1.vrPosition.setY(posicaoCaveira1Y);

                 caveiraFrente2 = createSprite(R.mipmap.caveira_frente, 1, 1);
                 caveiraFrente2.setScreenPercent(15, 10);
                 caveiraFrente2.vrPosition.setX(posicaoCaveira2X);
                 caveiraFrente2.vrPosition.setY(posicaoCaveira2Y);

                 caveiraFrente3 = createSprite(R.mipmap.caveira_frente, 1, 1);
                 caveiraFrente3.setScreenPercent(15, 10);
                 caveiraFrente3.vrPosition.setX(posicaoCaveira3X);
                 caveiraFrente3.vrPosition.setY(posicaoCaveira3Y);


                 setSceneBackgroundColor(0, 0, 0);//pinta a cor de fundo


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
            intervaloCaveira.update();

            if (AGInputManager.vrTouchEvents.screenClicked()) {



            if (botaoCima.collide(AGInputManager.vrTouchEvents.getLastPosition())) //verifica se a pessoa clicou no botao
            {
                posicaoBoboraX = posicaoBoboraX - 35;
                aboboraFrente.vrPosition.setX(posicaoBoboraX);
                        if(verificaColisaoObstaculo(aboboraFrente,1) == true)
                        {
                            posicaoBoboraX = posicaoBoboraX + 35;
                            aboboraFrente.vrPosition.setX(posicaoBoboraX);
                        }

            } else if (botaoBaixo.collide(AGInputManager.vrTouchEvents.getLastPosition())) {

                posicaoBoboraX = posicaoBoboraX + 35;
                aboboraFrente.vrPosition.setX(posicaoBoboraX);
                if(verificaColisaoObstaculo(aboboraFrente,2) == true)
                {
                    posicaoBoboraX = posicaoBoboraX - 35;
                    aboboraFrente.vrPosition.setX(posicaoBoboraX);
                }

            } else if (botaoEsquerda.collide(AGInputManager.vrTouchEvents.getLastPosition())) {

                posicaoBoboraY = posicaoBoboraY - 35;
                aboboraFrente.vrPosition.setY(posicaoBoboraY);
                if(verificaColisaoObstaculo(aboboraFrente,4) == true)
                {
                    posicaoBoboraY = posicaoBoboraY + 35;
                    aboboraFrente.vrPosition.setY(posicaoBoboraY);
                }

            } else if (botaoDireita.collide(AGInputManager.vrTouchEvents.getLastPosition())) {

                posicaoBoboraY = posicaoBoboraY + 35;
                aboboraFrente.vrPosition.setY(posicaoBoboraY);
                if(verificaColisaoObstaculo(aboboraFrente,3) == true)
                {
                    posicaoBoboraY = posicaoBoboraY - 35;
                    aboboraFrente.vrPosition.setY(posicaoBoboraY);
                }
            }


            if (AGInputManager.vrTouchEvents.backButtonClicked()) {
                vrGameManager.setCurrentScene(0); }

                float x = aboboraFrente.vrPosition.getX();

                float y = aboboraFrente.vrPosition.getY();

                System.out.println("X:" + x + "Y:" + y + "| pontuacao: "+ pontuacaoTotal);

        }//fim do if do Screen Clicked

            if( verificaColisaoDoce()==true || intervaloTempo.isTimeEnded() == true)
            {alteraPosDoceRandom(); intervaloTempo.restart();}

            if(intervaloCaveira.isTimeEnded())
            {persegue(caveiraFrente1);
             persegue(caveiraFrente2);
             persegue(caveiraFrente3);
            intervaloCaveira.restart();}

        }//fim do loop
    }




