package se.feomedia.assignment.system;

import com.artemis.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import se.feomedia.assignment.component.Render;
import se.feomedia.assignment.component.RenderRef;

public class RenderResolver extends BaseSystem {

    private ComponentMapper<RenderRef> renderableRefMapper;
    private ComponentMapper<Render> renderableMapper;

    @Override
    protected void processSystem() {
    }

    @Override
    protected void initialize() {

        world.getAspectSubscriptionManager()
                .get(Aspect.all(RenderRef.class).exclude(Render.class))
                .addSubscriptionListener(new SubscriptionAdapter(world) {

                    @Override
                    protected void inserted(Entity e) {

                        String fp = renderableRefMapper.get(e).filePath;

                        renderableMapper.create(e).sprite = new com.badlogic.gdx.graphics.g2d.Sprite(new Texture(Gdx.files.internal(fp)));
                    }

                    @Override
                    protected void removed(Entity e) {

                    }
                });
    }
}