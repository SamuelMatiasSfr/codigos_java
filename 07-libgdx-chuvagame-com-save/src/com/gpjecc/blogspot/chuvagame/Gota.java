package com.gpjecc.blogspot.chuvagame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Gota {
    private Texture gotaImage;
    private Sound gotaSound;
    private Rectangle corpoGota;

    public Gota(){
        gotaImage = new Texture(Gdx.files.internal("assets/droplet.png"));
        gotaSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"));
        corpoGota = new Rectangle();
        corpoGota.x = MathUtils.random(0, 800-64);
        corpoGota.y = 480;
        corpoGota.width = 64;
        corpoGota.height = 64;
    }

    public void andar(){
        corpoGota.y -= 200 * Gdx.graphics.getDeltaTime();
    }

    public boolean podeSumir(){
        if(corpoGota.y + 64 < 0){
            return true;
        }else{
            return false;
        }
    }

    public Rectangle getCorpo(){
        return corpoGota;
    }

    public Sound getSom(){
        return gotaSound;
    }

    public Texture getImage(){
        return gotaImage;
    }

    public void dispose() {
        gotaImage.dispose();
        gotaSound.dispose();
    }

    public void setPos(float x, float y){
        corpoGota.x = x;
        corpoGota.y = y;
    }
}
