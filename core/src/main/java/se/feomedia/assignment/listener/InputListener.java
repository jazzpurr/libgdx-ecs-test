package se.feomedia.assignment.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputListener extends InputAdapter {

    private OrthographicCamera camera;

    public InputListener(OrthographicCamera camera) {

        this.camera = camera;
    }

    @Override
    public boolean scrolled(int amount) {

        camera.zoom += amount * .1f;

        if (camera.zoom < .1f)
            camera.zoom = .1f;

        camera.update();

        return super.scrolled(amount);
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.ESCAPE){

            Gdx.app.exit();
        }

        return super.keyDown(keycode);
    }
}