package co.tantleff.libanimate;

import co.tantleff.libanimate.scripting.CutsceneScript;
import co.tantleff.libanimate.scripting.ScriptLoader;
import groovy.lang.Binding;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@SuppressWarnings("unused")
public final class Libanimate extends JavaPlugin {

    @Override
    public void onEnable() {
        // Grab the folder that scripts are to be stored in
        final var cutscenesFolder = getDataFolder().toPath().resolve("cutscenes");
        try {
            Files.createDirectories(cutscenesFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        saveResource("cutscenes/test.groovy", false);

        // Create a ScriptLoader to load scripts in that directory
        final var loader = new ScriptLoader<>(getDataFolder().toPath().resolve("cutscenes"), CutsceneScript.class);

        // make a command to run a simple test script
        Objects.requireNonNull(getCommand("testanimate")).setExecutor((commandSender, command, s, strings) -> {
            if (!(commandSender instanceof final Player caller))
                return false;

            caller.sendMessage("doin the command or somethin");

            // Load and run script
            try {
                final var script = loader.loadScript("test.groovy", new Binding());
                script.run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
