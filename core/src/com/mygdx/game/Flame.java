package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Flame {
    private ParticleEffect flame;
    private ParticleEmitter flameEmitter;

    private Vector2 position;

    private Spaceship spaceship;

    private float angle;

    public Flame(Spaceship spaceship) {
        position = new Vector2(-30, 0);

        flame = new ParticleEffect();
        flame.load(Gdx.files.internal("flame.p"), Gdx.files.internal("."));
        flameEmitter = flame.findEmitter("Flame");

        this.spaceship = spaceship;
        angle = spaceship.angle();
    }

    public void update() {
        float difference = (spaceship.angle() - angle) * MathUtils.radiansToDegrees;
        angle = spaceship.angle();

        position.rotateDeg(difference);

        ParticleEmitter.ScaledNumericValue oldAngle = flameEmitter.getAngle();
        oldAngle.setHigh(oldAngle.getHighMin() + difference, oldAngle.getHighMax() + difference);
        oldAngle.setLow(oldAngle.getLowMin() + difference);

        flame.setPosition(
                spaceship.x() + spaceship.width() / 2 + position.x,
                spaceship.y() + spaceship.height() / 2 + position.y
        );

    }

    public void draw(SpriteBatch batch) {
        flame.draw(batch, Gdx.graphics.getDeltaTime());
    }

}
