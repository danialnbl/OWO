package com.sendiribuat.helloworld.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sendiribuat.helloworld.helloworld;

public class Tile extends Box{

    private TextureRegion light;
    private TextureRegion dark;

    private boolean selected;

    public Tile(float x, float y, float width,float height) {

        this.x = x;
        this.y = y;
        this.width = width - 8;
        this.height = height- 8;

        light = helloworld.res.getAtlas("pack").findRegion("light");
        dark = helloworld.res.getAtlas("pack").findRegion("dark");
    }

    public void update(float dt){}
    public void render(SpriteBatch sb){
        sb.draw(light, x-width / 2 , y-width / 2, width, height);
    }

}
