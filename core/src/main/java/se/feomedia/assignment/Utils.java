package se.feomedia.assignment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public final class Utils {

    private Utils(){}

    public static void log(Object callingObject, Object objectToLog) {

        String s = callingObject.getClass().getName();
        Gdx.app.log(s.split("\\.")[s.split("\\.").length - 1], "" + objectToLog);
    }

    public static Rectangle cameraBounds2D(OrthographicCamera camera) {

        return new Rectangle(
                camera.position.x - camera.viewportWidth * .5f * camera.zoom,
                camera.position.y - camera.viewportHeight * .5f * camera.zoom,
                camera.position.x + camera.viewportWidth * .5f * camera.zoom,
                camera.position.y + camera.viewportHeight * .5f * camera.zoom);
    }
}