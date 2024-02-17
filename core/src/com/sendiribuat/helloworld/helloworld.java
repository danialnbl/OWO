package com.sendiribuat.helloworld;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendiribuat.helloworld.handler.Content;
import com.sendiribuat.helloworld.states.GSM;
import com.sendiribuat.helloworld.states.MenuState;
import com.sendiribuat.helloworld.states.PlayState;

public class helloworld extends ApplicationAdapter {

	public static final String TITLE = "Owo";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

    public static Content res;
	private SpriteBatch sb;
	private GSM gsm;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);

        res = new Content();
        res.loadAtlas("pack.pack","pack");

		sb = new SpriteBatch();
		gsm = new GSM();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
}
