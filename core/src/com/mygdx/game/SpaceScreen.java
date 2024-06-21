package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpaceScreen implements Screen {
    final float CAM_DAMPING_X = 0.02f;
    final float CAM_DAMPING_Y = 0.02f;
    final float FOLLOW_UP_RADIUS = 150;

    MyGame game;

    OrthographicCamera camera;
    Color bgColor;
    Spaceship spaceship;
    Meteor meteor;
    Flame flame;

    public SpaceScreen(MyGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bgColor = Color.valueOf("1C3D6E");

        spaceship = new Spaceship();
        // set ship to be in center of the camera
        camera.position.set(
                spaceship.x() + spaceship.width() / 2f,
                spaceship.y() + spaceship.height() / 2f,
                0.0f
        );

        meteor = new Meteor();

        flame = new Flame(spaceship);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spaceship.update();
        flame.update();

        final float shipCenterX = spaceship.x() + spaceship.width() / 2f;
        final float shipCenterY = spaceship.y() + spaceship.height() / 2f;
        final float screenCenterX = Gdx.graphics.getWidth() /2.f;
        final float screenCenterY = Gdx.graphics.getHeight() /2.f;
        // Calculate ship to screen center distance in screen space.
        Vector3 dist = new Vector3();
        dist.set(shipCenterX, shipCenterY, 0); // ship position in world space
        camera.project(dist); // ship position in screen space
        dist.sub(screenCenterX, screenCenterY, 0);

        // If ship is outside of range follow with camera.
        if (dist.len2() - FOLLOW_UP_RADIUS * FOLLOW_UP_RADIUS > 0) {
            float camX = MathUtils.lerp(camera.position.x, shipCenterX, CAM_DAMPING_X);
            float camY = MathUtils.lerp(camera.position.y, shipCenterY, CAM_DAMPING_Y);

            camera.position.set(camX, camY, 0.0f);
        }
        camera.update();

        ScreenUtils.clear(bgColor);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        flame.draw(game.batch);
        spaceship.draw(game.batch);
        meteor.draw(game.batch);
        game.batch.end();

        Vector3 screenCenter = new Vector3();
        screenCenter.set(screenCenterX, screenCenterY, 0);
        camera.unproject(screenCenter);
        game.shapeRenderer.setProjectionMatrix(camera.combined);
        game.shapeRenderer.setAutoShapeType(true);
        game.shapeRenderer.begin();
        game.shapeRenderer.circle(screenCenter.x, screenCenter.y, FOLLOW_UP_RADIUS);
        game.shapeRenderer.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
