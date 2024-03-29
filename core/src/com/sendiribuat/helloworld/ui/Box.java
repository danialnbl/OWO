package com.sendiribuat.helloworld.ui;

public class Box {
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public boolean contains(float x, float y){
        return x > this.x - width / 2 &&
                x < this.x + width / 2 &&
                y > this.y - width / 2 &&
                y < this.y + width / 2;
    }

}
