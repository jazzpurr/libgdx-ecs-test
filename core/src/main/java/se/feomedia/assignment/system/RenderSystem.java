package se.feomedia.assignment.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import javafx.geometry.Pos;
import se.feomedia.assignment.component.Position;
import se.feomedia.assignment.component.Renderable;

public class RenderSystem extends IteratingSystem {

    @Wire
    private SpriteBatch spriteBatch;

    @Wire
    private OrthographicCamera camera;

    private ComponentMapper<Renderable> renderableMapper;
    private ComponentMapper<Position> positionMapper;

    public RenderSystem() {
        super(Aspect.all(Renderable.class, Position.class));
    }

    @Override
    protected void begin() {

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
    }

    @Override
    protected void end() {

        spriteBatch.end();
    }

    @Override
    protected void process(int entityId) {

        Vector2 pos = positionMapper.get(entityId).position;

        renderableMapper.get(entityId).sprite.setCenter(pos.x, pos.y);
        renderableMapper.get(entityId).sprite.draw(spriteBatch);
    }
}