package se.feomedia.assignment.system;

import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import se.feomedia.assignment.component.Scene;

import static com.artemis.Aspect.all;

public class CameraPanSystem extends IteratingSystem {

    @Wire
    private OrthographicCamera camera;

    public CameraPanSystem() {
        super(all(Scene.class));
    }

    @Override
    protected void process(int entityId) {

        float camSpeed = 10f;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

            camera.position.y += camSpeed * camera.zoom;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

            camera.position.y -= camSpeed * camera.zoom;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            camera.position.x -= camSpeed * camera.zoom;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            camera.position.x += camSpeed * camera.zoom;
        }
    }
}