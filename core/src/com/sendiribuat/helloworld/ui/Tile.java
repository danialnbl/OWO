package com.sendiribuat.helloworld.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sendiribuat.helloworld.helloworld;

public class Tile extends Box{

    private TextureRegion light;
    private TextureRegion dark;

    private boolean selected;

    private float totalWidth;
    private float totalHeight;
    private float timer;
    private float maxTime = 0.5f;

    public Tile(float x, float y, float width,float height) {

        this.x = x;
        this.y = y;
        this.totalWidth = width - 8;
        this.totalHeight = height - 8;

        light = helloworld.res.getAtlas("pack").findRegion("light");
        dark = helloworld.res.getAtlas("pack").findRegion("dark");
    }

    public void setTimer(float t){
        timer = t;
    }
    public void setSelected(boolean b){
        selected = b;
    }

    public boolean isSelected(){
        return selected;
    }

    public void toggleSelected(){
        selected = !selected;
    }

    public void update(float dt){
        if(width < totalWidth && height < totalHeight){
            timer += dt;
            width = (timer / maxTime) * totalWidth;
            height = (timer / maxTime) * totalHeight;
            if(width < 0) width = 0;
            if(height < 0) height = 0;
            if(width > totalWidth){
                width = totalWidth;
            }
            if(height > totalHeight){
                height = totalHeight;
            }
        }

    }
    public void render(SpriteBatch sb){
        if(selected){
            sb.draw(light, x-width / 2 , y-width / 2, width, height);
        }else{
            sb.draw(dark, x-width / 2 , y-width / 2, width, height);
        }
    }
}
