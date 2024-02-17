package com.sendiribuat.helloworld.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sendiribuat.helloworld.helloworld;

public class TextImage extends Box{

    private TextureRegion[][] fontSheet;
    private String text;

    public TextImage(String text, float x, float y) {
        this.text = text;
        this.x = x;
        this.y = y;

        int size = 50;
        width = size * text.length();
        height = size;

        TextureRegion sheet = helloworld.res.getAtlas("pack").findRegion("fontsheet");
        int numCols = sheet.getRegionWidth() / size;
        int numRows = sheet.getRegionHeight() / size;
        fontSheet = new TextureRegion[numRows][numCols];

        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols; col++){
                fontSheet[row][col] = new TextureRegion(sheet,col * size,row * size,size,size);
            }
        }
    }

    public void render(SpriteBatch sb){
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            c -= 'a';
            int index = (int)c;
            int row = index / fontSheet[0].length;
            int col = index % fontSheet[0].length;
            sb.draw(fontSheet[row][col],x - width / 2 + 50 * i,
                    y - height / 2);
        }
    }
}
