package com.gpjecc.blogspot.chuvagame;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

   @Override
   public void create() {
      // load the drop sound effect and the rain background "music"
      rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"));

      // start the playback of the background music immediately
      rainMusic.setLooping(true);
      rainMusic.play();

      // create the camera and the SpriteBatch
      camera = new OrthographicCamera();
      camera.setToOrtho(false, 800, 480);
      batch = new SpriteBatch();

      // create a Rectangle to logically represent the bucket
      balde = new Balde();

      // create the raindrops array and spawn the first raindrop
      gotas = new Array<Gota>();
      spawnGotas();
   }

   private void spawnGotas() {
      Gota gota = new Gota();
      gotas.add(gota);
      lastDropTime = TimeUtils.nanoTime();
   }

   @Override
   public void render() {
      // clear the screen with a dark blue color. The
      // arguments to glClearColor are the red, green
      // blue and alpha component in the range [0,1]
      // of the color to be used to clear the screen.
      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      // tell the camera to update its matrices.
      camera.update();

      // tell the SpriteBatch to render in the
      // coordinate system specified by the camera.
      batch.setProjectionMatrix(camera.combined);

      // begin a new batch and draw the bucket and
      // all drops
      batch.begin();
      batch.draw(balde.getImage(), balde.getCorpo().x, balde.getCorpo().y);
      for(Gota gota: gotas) {
         Rectangle corpoGota = gota.getCorpo();
         batch.draw(gota.getImage(), corpoGota.x, corpoGota.y);
      }
      batch.end();

      // process user input
      if(Gdx.input.isTouched()) {
         Vector3 touchPos = new Vector3();
         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
         camera.unproject(touchPos);
         balde.mudarPosicao(touchPos);
         //System.out.println("Mouse");
      }

      balde.andar();

      // make sure the bucket stays within the screen bounds
      balde.limitar();

      // check if we need to create a new raindrop
      if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnGotas();

      // move the raindrops, remove any that are beneath the bottom edge of
      // the screen or that hit the bucket. In the later case we play back
      // a sound effect as well.
      Iterator<Gota> iter = gotas.iterator();      
      while(iter.hasNext()) {
         Gota gota = iter.next(); 
         Rectangle corpoGota = gota.getCorpo();        
         gota.andar();
         if(gota.podeSumir()) iter.remove();
         if(corpoGota.overlaps(balde.getCorpo())) {
            gota.getSom().play();
            iter.remove();
         }
      }
   }

   @Override
   public void dispose() {
      // dispose of all the native resources
      balde.dispose();
      for(Gota gota : gotas){
         gota.dispose();
      }
      rainMusic.dispose();
      batch.dispose();
   }
}