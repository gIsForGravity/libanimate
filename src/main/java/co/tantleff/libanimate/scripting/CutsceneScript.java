package co.tantleff.libanimate.scripting;

import groovy.lang.Script;
import org.bukkit.Bukkit;

public abstract class CutsceneScript extends Script {
    public void sayHi() {
        Bukkit.broadcastMessage("he said hi or somethin");
    }
}
