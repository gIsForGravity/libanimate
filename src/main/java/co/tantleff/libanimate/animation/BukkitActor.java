package co.tantleff.libanimate.animation;

import org.bukkit.entity.Entity;

public class BukkitActor implements Actor {
    private final Entity entity;

    public BukkitActor(Entity entity) {
        this.entity = entity;
    }
}
