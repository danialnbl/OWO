package com.sendiribuat.helloworld.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendiribuat.helloworld.helloworld;
import com.sendiribuat.helloworld.ui.Graphic;
import com.sendiribuat.helloworld.ui.TextImage;

public class MenuState extends State {

    private Graphic title;
    private TextImage play;

    public MenuState(GSM gsm) {
        super(gsm);

        title = new Graphic(
                helloworld.res.getAtlas("pack").findRegion("owo"),
                helloworld.WIDTH / 2,
                helloworld.HEIGHT / 2 + 100);

        play = new TextImage("play", helloworld.WIDTH / 2, helloworld.HEIGHT / 2 - 50);
    }

    public void handleInput() {
        if(Gdx.input.isTouched()){
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            cam.unproject(mouse);
            if(play.contains(mouse.x,mouse.y)){
                gsm.set(new PlayState(gsm, PlayState.Difficulty.EASY));
            }
        }
    }
    public void update(float dt) {
        handleInput();
    }
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        title.render(sb);
        play.render(sb);
        sb.end();
    }

}
