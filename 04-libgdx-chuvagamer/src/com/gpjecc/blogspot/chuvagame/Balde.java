package com.gpjecc.blogspot.chuvagame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

//bucket

public class Balde {
    private Texture baldeImage;
    private Rectangle corpoBalde;

    public Balde(){
        baldeImage = new Texture(Gdx.files.internal("assets/bucket.png"));
        corpoBalde = new Rectangle();
        corpoBalde.x = (800 / 2) - (64 / 2); 
        corpoBalde.y = 20; 
        corpoBalde.width = 64;
        corpoBalde.height = 64;
    }

    public void mudarPosicao(Vector3 touchPos){
        corpoBalde.x = (touchPos.x - 64);
    }

    public void andar(){
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            corpoBalde.x -= 200 * Gdx.graphics.getDeltaTime();
        } 
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            corpoBalde.x += 200 * Gdx.graphics.getDeltaTime();
        }
    }

    public void limitar(){
        if(corpoBalde.x < 0) corpoBalde.x = 0;
        if(corpoBalde.x > (800 - 64)) corpoBalde.x = 800 - 64;
    }

    public Rectangle getCorpo(){
        return corpoBalde;
    }

    public Texture getImage(){
        return baldeImage;
    }

    public void dispose() {
      // dispose of all the native resources
        baldeImage.dispose();
    }
}
