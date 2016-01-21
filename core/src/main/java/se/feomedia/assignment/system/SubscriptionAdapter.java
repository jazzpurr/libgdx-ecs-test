package se.feomedia.assignment.system;

import com.artemis.Entity;
import com.artemis.EntitySubscription;
import com.artemis.World;
import com.artemis.utils.IntBag;

public abstract class SubscriptionAdapter implements EntitySubscription.SubscriptionListener {
    private final World world;

    public SubscriptionAdapter(World world) {
        this.world = world;
    }

    @Override
    public final void inserted(IntBag entities) {
        int[] ids = entities.getData();
        for (int i = 0; i < entities.size(); i++)
            inserted(world.getEntity(ids[i]));
    }

    protected abstract void inserted(Entity e);

    @Override
    public final void removed(IntBag entities) {
        int[] ids = entities.getData();
        for (int i = 0; i < entities.size(); i++)
            removed(world.getEntity(ids[i]));
    }

    protected abstract void removed(Entity e);
}