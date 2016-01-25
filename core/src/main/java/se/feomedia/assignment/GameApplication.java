package se.feomedia.assignment;

import com.artemis.ComponentMapper;
import com.artemis.EntityEdit;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import se.feomedia.assignment.component.*;
import se.feomedia.assignment.listener.InputListener;
import se.feomedia.assignment.system.*;

@Wire
public class GameApplication extends Game {

    World world;

    private ComponentMapper<Shape> shapeMapper;

    @Override
    public void create() {

        OrthographicCamera cam;

        // World init
        WorldConfiguration config = new WorldConfiguration()
                .register(cam = new OrthographicCamera(1920, 1080))
                .register(new ShapeRenderer())
                .register(new SpriteBatch())
                .setSystem(new SnakeSystem())
                .setSystem(new SoundEffectResolver())
                .setSystem(new RenderResolver())
                .setSystem(new RenderSystem())
                .setSystem(new ShapeOutlineSystem())
                .setSystem(new MovementSystem())
                .setSystem(new EdgeBounceSystem())
                .setSystem(new CameraPanSystem());
        world = new World(config);
        world.inject(this);
        world.process();

        // Input
        Gdx.input.setInputProcessor(new InputListener(cam));

        // Scene
        int scene = world.create();
        EntityEdit sceneEdit = world.edit(scene);
        sceneEdit.create(Scene.class);

        // Ball
        int ball = world.create();
        EntityEdit ballEdit = world.edit(ball);
        ballEdit.create(SnakePosition.class);
        ballEdit.create(Position.class);
        ballEdit.create(Size.class).size.set(100, 100);
        ballEdit.create(Shape.class);
        ballEdit.create(Movement.class);
//        ballEdit.create(RenderRef.class).filePath = "player.png";
        ballEdit.create(SoundEffectRef.class).filePath = "laser6.mp3";
        shapeMapper.get(ball).filled = true;
        shapeMapper.get(ball).shapeColor = Color.GREEN;
        shapeMapper.get(ball).shapeGeometry = Shape.ShapeGeometry.RECTANGLE;

        // Paddle
        int paddle = world.create();
        EntityEdit paddleEdit = world.edit(paddle);
        paddleEdit.create(Position.class);
        paddleEdit.create(Size.class).size.set(100, 100);
        paddleEdit.create(Shape.class);
        shapeMapper.get(paddle).filled = true;
        shapeMapper.get(paddle).shapeColor = Color.BLUE;
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.setDelta(Math.min(1f / 30f, Gdx.graphics.getDeltaTime()));
        world.process();
    }
}