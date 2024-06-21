package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Spaceship {
    public static final float MAX_SPEED = 500;
    public static final float STEERING_FACTOR = 5;

    Vector2 pos;
    private float rot;
    Vector2 vel;
    TextureRegion texture;

    public Spaceship() {
        texture = new TextureRegion(new Texture(Gdx.files.internal("ship_L.png")));
        pos = new Vector2();
        vel = new Vector2();
    }

    public void update() {
        Vector2 dir = new Vector2();

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dir.add(0, 1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dir.add(0, -1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dir.add(-1, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dir.add(1, 0);
        }

        dir.nor();

        Vector2 desiredVelocity = new Vector2();
        desiredVelocity.set(dir);
        desiredVelocity.scl(MAX_SPEED);

        Vector2 steeringVector = new Vector2();
        steeringVector.set(desiredVelocity).sub(vel);
        steeringVector.scl(Gdx.graphics.getDeltaTime());
        steeringVector.scl(STEERING_FACTOR);
        vel.add(steeringVector);

        Vector2 shipVelDelta = new Vector2();
        shipVelDelta.set(vel).scl(Gdx.graphics.getDeltaTime());

        rot = MathUtils.atan2(shipVelDelta.y, shipVelDelta.x); // rad
        pos.add(shipVelDelta);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                texture,
            pos.x, pos.y,
            texture.getRegionWidth() / 2.f, texture.getRegionHeight() / 2.f,
            texture.getRegionWidth(), texture.getRegionHeight(),
            1.0f, 1.0f,
            rot * MathUtils.radiansToDegrees
        );
    }

    public float x() {
        return pos.x;
    }

    public float y() {
        return pos.y;
    }

    public float height() {
        return texture.getRegionHeight();
    }

    public float width() {
        return texture.getRegionWidth();
    }

    public float angle() {
        return rot;
    }
}
