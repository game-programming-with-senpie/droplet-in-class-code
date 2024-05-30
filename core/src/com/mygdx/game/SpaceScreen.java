package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpaceScreen implements Screen {
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
        meteor = new Meteor();

        flame = new Flame(spaceship);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        spaceship.update();

        flame.update();

        camera.position.set(
            spaceship.x() + spaceship.width() / 2f,
            spaceship.y() + spaceship.height() / 2f,
            0.0f
        );
        camera.update();

        ScreenUtils.clear(bgColor);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        flame.draw(game.batch);
        spaceship.draw(game.batch);
        meteor.draw(game.batch);
        game.batch.end();
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
