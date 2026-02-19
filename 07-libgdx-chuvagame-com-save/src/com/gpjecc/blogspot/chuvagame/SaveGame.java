package com.gpjecc.blogspot.chuvagame;

import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class SaveGame {
    
    private Preferences preferences;

    public SaveGame(){
        preferences = Gdx.app.getPreferences("arquivoSave");
    }

    public void savePontos(int pontos){
        preferences.putInteger("pontos", pontos);
        preferences.flush();
    }

    public void saveVidas(int vidas){
        preferences.putInteger("vidas", vidas);
        preferences.flush();
    }

    public void savePosBalde(Rectangle corpo){
        preferences.putFloat("posXbalde", corpo.x);
        preferences.putFloat("posYbalde", corpo.y);
        preferences.flush();
    }

    public void saveGotas(Array<Gota> gotas){
        preferences.putInteger("quantGotas", gotas.size);
        for(int i=0; i<gotas.size; i++){
            Rectangle corpo = gotas.get(i).getCorpo();
            preferences.putFloat("gota"+i+"x", corpo.x);
            preferences.putFloat("gota"+i+"y", corpo.y);
        }
        preferences.flush();
    }

    public Array<Gota> getGotas(){
        Array<Gota> gotas = new Array<>();
        
        int cont = preferences.getInteger("quantGotas", 0);

        for(int i=0; i<cont; i++){
            float x = preferences.getFloat("gota"+i+"x", MathUtils.random(0, 800-64));
            float y = preferences.getFloat("gota"+i+"y", 480);
            Gota gota = new Gota();
            gota.setPos(x, y);
            gotas.add(gota);
        }

        return gotas;
    }

    public int getPontos(){
        return preferences.getInteger("pontos", 0);
    }

    public int getVidas(){
        return preferences.getInteger("vidas", 3);
    }

    public float getPosXbalde(){
        return preferences.getFloat("posXbalde", (800 / 2) - (64 / 2));
    }

    public float getPosYbalde(){
        return preferences.getFloat("posYbalde", 20);
    }
}
