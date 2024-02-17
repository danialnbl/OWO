package com.sendiribuat.helloworld.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sendiribuat.helloworld.helloworld;
import com.sendiribuat.helloworld.ui.Tile;

public class PlayState extends State {

    private Tile[][] tiles;
    private int tileSize;
    private float boardOffset;

    public PlayState(GSM gsm) {

        super(gsm);

        //480 width
        //800 height
        tiles = new Tile[6][6];
        tileSize = helloworld.WIDTH / tiles[0].length;
        boardOffset = (helloworld.HEIGHT - (tileSize * tiles.length)) / 2;
        for(int row = 0; row < tiles.length; row++){
            for(int col = 0; col < tiles[0].length; col++){
                tiles[row][col] = new Tile(col * tileSize + tileSize / 2, row * tileSize + boardOffset + tileSize / 2, tileSize, tileSize);
            }
        }
    }

    public void handleInput() {

    }

    public void update(float dt) {
//        System.out.println("Test state updating");
    }

    public void render(SpriteBatch sb) {
//        System.out.println("Test state rendering");
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        for(int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                tiles[row][col].render(sb);
            }
        }
        sb.end();
    }
}
