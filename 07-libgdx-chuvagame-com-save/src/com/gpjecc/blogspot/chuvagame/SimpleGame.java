package com.gpjecc.blogspot.chuvagame;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SimpleGame extends ApplicationAdapter {
  
   private Music rainMusic;
   private SpriteBatch batch;
   private OrthographicCamera camera;   
   private Array<Gota> gotas;
   private Balde balde;
   private long lastDropTime;

   private int vidas = 3;
	private int pontos = 0;

	private BitmapFont font;

   private SaveGame save;

   @Override
   public void create() {

      Texture.setEnforcePotImages(false);

      save = new SaveGame();

		font = new BitmapFont();
		font.setColor(Color.LIGHT_GRAY);

      rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"));

      rainMusic.setLooping(true);
      rainMusic.play();

      camera = new OrthographicCamera();
      camera.setToOrtho(false, 800, 480);
      batch = new SpriteBatch();

      balde = new Balde(save);

      vidas = save.getVidas();
      pontos = save.getPontos();

      gotas = save.getGotas();
      spawnGotas();
   }

   private void spawnGotas() {
      Gota gota = new Gota();
      gotas.add(gota);
      lastDropTime = TimeUtils.nanoTime();
   }

   @Override
   public void render() {
      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      camera.update();

      batch.setProjectionMatrix(camera.combined);

      batch.begin();
      batch.draw(balde.getImage(), balde.getCorpo().x, balde.getCorpo().y);
      for(Gota gota: gotas) {
         Rectangle corpoGota = gota.getCorpo();
         batch.draw(gota.getImage(), corpoGota.x, corpoGota.y);
      }
      
		font.draw(batch, "Vidas = " + vidas, 680, 470);
		font.draw(batch, "Pontos = " + pontos, 680, 450);

      batch.end();

      pegaEntrada();

      if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnGotas();

      Iterator<Gota> iter = gotas.iterator();      
      while(iter.hasNext()) {
         Gota gota = iter.next(); 
         Rectangle corpoGota = gota.getCorpo();        
         gota.andar();

         if(gota.podeSumir()){
            iter.remove();
				vidas--;
				if (vidas <= 0) {
               save.savePontos(0);
               save.saveVidas(3);
					Gdx.app.exit();
					System.exit(-1);
            }
         } 

         if(corpoGota.overlaps(balde.getCorpo())) {
            gota.getSom().play();
            pontos++;
            iter.remove();
         }
      }
      save.saveGotas(gotas);
      save.savePosBalde(balde.getCorpo());
      save.savePontos(pontos);
      save.saveVidas(vidas);
   }

   private void pegaEntrada(){
      if(Gdx.input.isTouched()) {
         Vector3 touchPos = new Vector3();
         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
         camera.unproject(touchPos);
         balde.mudarPosicao(touchPos);
      }

      balde.andar();
      balde.limitar();
   }

   @Override
   public void dispose() {
      balde.dispose();
      for(Gota gota : gotas){
         gota.dispose();
      }
      rainMusic.dispose();
      batch.dispose();
   }
}