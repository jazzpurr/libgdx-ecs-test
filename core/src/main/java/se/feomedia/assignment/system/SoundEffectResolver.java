package se.feomedia.assignment.system;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import se.feomedia.assignment.component.SoundEffect;
import se.feomedia.assignment.component.SoundEffectRef;

/**
 * Created by Anton on 2016-01-22.
 */
public class SoundEffectResolver extends BaseSystem {


    private ComponentMapper<SoundEffect> soundEffectMapper;
    private ComponentMapper<SoundEffectRef> soundEffectRefMapper;

    @Override
    protected void processSystem() {
    }

    @Override
    protected void initialize() {

        world.getAspectSubscriptionManager()
                .get(Aspect.all(SoundEffectRef.class).exclude(SoundEffect.class))
                .addSubscriptionListener(new SubscriptionAdapter(world) {

                    @Override
                    protected void inserted(Entity e) {

                        String fp = soundEffectRefMapper.get(e).filePath;

                        soundEffectMapper.create(e).sound = Gdx.audio.newSound(Gdx.files.internal(fp));
                    }

                    @Override
                    protected void removed(Entity e) {

                    }
                });
    }
}