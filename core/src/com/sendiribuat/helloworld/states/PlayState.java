package com.sendiribuat.helloworld.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.sendiribuat.helloworld.helloworld;
import com.sendiribuat.helloworld.ui.Tile;

public class PlayState extends State {

    private final int MAX_FINGERS = 2;

    public enum Difficulty{
        EASY,
        NORMAL,
        HARD,
        INSANE;
    }

    private int level;
    private int maxLevel;
    private Difficulty difficulty;
    private int[] args;

    private Tile[][] tiles;
    private int tileSize;
    private float boardOffset;
    private int boardHeight;

    //player attempt to finish
    private Array<Tile> selected;
    //solution
    private Array<Tile> finished;

    private boolean showing;
    private float timer;

    public PlayState(GSM gsm, Difficulty difficulty) {

        super(gsm);

        level = 1;
        this.difficulty = difficulty;

        selected = new Array<Tile>();
        finished = new Array<Tile>();

        args = getArgs();
        createBoard(args[0],args[1]);
        createFinished(args[2]);
    }

    private int[] getArgs() {
        int[] ret = new int[3];
        if(difficulty ==  Difficulty.EASY) {
            ret[0] = 3;
            ret[1] = 3;
            if(level >= 1 && level <= 3){
                ret[2] = 3;
            }else if(level == 4 || level == 5) {
                ret[2] = 4;
            }
            maxLevel = 5;
        }
        if(difficulty == Difficulty.NORMAL){
            ret[0] = 4;
            ret[1] = 4;
            if(level == 1 || level == 2) {
                ret[2] = 4;
            }
            if(level == 3 || level == 4) {
                ret[2] = 5;
            }
            if(level == 5 || level == 6) {
                ret[2] = 6;
            }
            maxLevel = 6;
        }
        if(difficulty == Difficulty.HARD){
            ret[0] = 5;
            ret[1] = 5;
            if(level == 1 || level == 2) {
                ret[2] = 6;
            }
            if(level == 3 || level == 4) {
                ret[2] = 7;
            }
            if(level == 5 || level == 6) {
                ret[2] = 8;
            }
            if(level == 7 || level == 8) {
                ret[2] = 9;
            }
            maxLevel = 8;
        }
        if(difficulty == Difficulty.INSANE){
            ret[0] = 6;
            ret[1] = 6;
            if(level == 1 || level == 2) {
                ret[2] = 8;
            }
            if(level == 3 || level == 4) {
                ret[2] = 9;
            }
            if(level == 5 || level == 6) {
                ret[2] = 10;
            }
            if(level == 7 || level == 8) {
                ret[2] =11;
            }
            if(level == 9 || level == 10) {
                ret[2] = 12;
            }
            maxLevel = 10;
        }
        return ret;
    }

    private void createBoard(int numRows, int numCols) {
        tiles = new Tile[numRows][numCols];
        tileSize = helloworld.WIDTH / tiles[0].length;
        boardHeight = tileSize * tiles.length;
        boardOffset = (helloworld.HEIGHT - boardHeight) / 2;
        for(int row = 0; row < tiles.length; row++){
            for(int col = 0; col < tiles[0].length; col++){
                tiles[row][col] = new Tile(col * tileSize + tileSize / 2, row * tileSize + boardOffset + tileSize / 2, tileSize, tileSize);

                tiles[row][col].setTimer((-(tiles.length - row) - col) * 0.05f);
            }
        }
    }

    public void createFinished(int numTilesToLight){
        showing = true;
        timer = 0;

        selected.clear();
        finished.clear();
        for(int i = 0; i < numTilesToLight; i++){
            int row = 0;
            int col = 0;
            do {
                row = MathUtils.random(tiles.length - 1);
                col = MathUtils.random(tiles[0].length - 1);
            } while (finished.contains(tiles[row][col], true));
            finished.add(tiles[row][col]);
            tiles[row][col].setSelected(true);
        }
    }

    public void checkShowing(float dt){
        if(showing){
            timer += dt;
            if(timer > 2){
                if(timer % 0.15f < 0.07f){
                    for(int i = 0; i < finished.size; i++){
                        finished.get(i).setSelected(true);
                    }
                }else{
                    for(int i = 0; i < finished.size; i++){
                        finished.get(i).setSelected(false);
                    }
                }
            }
            if(timer > 4){
                showing = false;
                for(int i = 0; i < finished.size; i++){
                    finished.get(i).setSelected(false);
                }
            }
        }
    }

    public boolean isFinished(){
        for(int i = 0; i < finished.size; i++){
            Tile tf = finished.get(i);
            if(!selected.contains(tf, true)){
                return false;
            }
        }
        return true;
    }

    public void done(){
        Gdx.app.exit();
    }

    public void handleInput() {
        for(int i = 0; i < MAX_FINGERS; i++){

            if(!showing && Gdx.input.isTouched(i)){
                mouse.x = Gdx.input.getX(i);
                mouse.y = Gdx.input.getY(i);
                cam.unproject(mouse);

                if(mouse.x > 0 && mouse.x < helloworld.WIDTH && mouse.y > boardOffset && mouse.y < boardOffset + boardHeight) {
                    int row = (int) ((mouse.y - boardOffset) / tileSize);
                    int col = (int) (mouse.x / tileSize);
                    if(!tiles[row][col].isSelected()){
                        tiles[row][col].setSelected(true);
                        selected.add(tiles[row][col]);
                        if(isFinished()){
                            level++;
                            if (level > maxLevel){
                                done();
                            }
                            args = getArgs();
                            createBoard(args[0],args[1]);
                            createFinished(args[2]);
                        }
                    }
                }
            }

        }
    }

    public void update(float dt) {
        //System.out.println("Test state updating");
        handleInput();

        checkShowing(dt);


        for(int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                tiles[row][col].update(dt);
            }
        }
    }

    public void render(SpriteBatch sb) {
        //System.out.println("Test state rendering");
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
