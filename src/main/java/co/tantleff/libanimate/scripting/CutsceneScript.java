package co.tantleff.libanimate.scripting;

import groovy.lang.Script;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;

@SuppressWarnings("unused")
public abstract class CutsceneScript extends Script {
    public final EntityType ZOMBIE = EntityType.ZOMBIE;
    public final EntityType PLAYER = EntityType.PLAYER;

    public void sayHi(EntityType type) {
        Bukkit.broadcastMessage("he said hi or somethin: " + type.getKey());
    }
}
