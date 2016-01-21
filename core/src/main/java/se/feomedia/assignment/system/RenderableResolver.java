package se.feomedia.assignment.system;

import com.artemis.*;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.feomedia.assignment.component.Renderable;
import se.feomedia.assignment.component.RenderableRef;

/**
 * Created by Anton on 2016-01-21.
 */
public class RenderableResolver extends BaseSystem {

    private ComponentMapper<RenderableRef> renderableRefMapper;
    private ComponentMapper<Renderable> renderableMapper;

    @Override
    protected void processSystem() {
    }

    @Override
    protected void initialize() {

        world.getAspectSubscriptionManager()
                .get(Aspect.all(RenderableRef.class).exclude(Renderable.class))
                .addSubscriptionListener(new SubscriptionAdapter(world) {

                    @Override
                    protected void inserted(Entity e) {

                        String fp = renderableRefMapper.get(e).filePath;

                        renderableMapper.create(e).sprite = new Sprite(new Texture(Gdx.files.internal(fp)));
                    }

                    @Override
                    protected void removed(Entity e) {

                    }
                });
    }
}