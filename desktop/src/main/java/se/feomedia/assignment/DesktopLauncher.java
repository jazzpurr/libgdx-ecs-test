package se.feomedia.assignment;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] args) {

        int width = 1920;
        int height = 1080;

        if (args.length == 2) {
            width = Integer.valueOf(args[0]);
            height = Integer.valueOf(args[1]);
        }

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.resizable = true;
        config.width = width;
        config.height = height;
        config.stencil = 8;
        config.title = "AL ECS GAME";
        new LwjglApplication(new GameApplication(), config);
    }
}