package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Meteor {
   public Vector2 pos;
   public Vector2 vel;
   public TextureRegion texture;

    public Meteor() {
        pos = new Vector2();
        vel = new Vector2();
        texture = new TextureRegion(new Texture(Gdx.files.internal("meteor.png")));
    }

    public void update () {

    }

    public void draw (Batch batch) {
       batch.draw(texture, pos.x, pos.y);
    }
}
