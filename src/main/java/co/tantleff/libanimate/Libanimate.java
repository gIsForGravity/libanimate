package co.tantleff.libanimate;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@SuppressWarnings("unused")
public final class Libanimate extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("testanimate")).setExecutor((commandSender, command, s, strings) -> {
            if (!(commandSender instanceof final Player caller))
                return false;

            caller.sendMessage("doin the command or somethin");

            caller.setGameMode(GameMode.SPECTATOR);

            final var beginningLocation = caller.getLocation().add(0, 3, 0);
            final var endLocation = beginningLocation.add(15, 0, 0);

            assert caller.getLocation().getWorld() != null;

            caller.getLocation().getWorld().spawn(beginningLocation, ItemDisplay.class, itemDisplay -> {
                caller.setSpectatorTarget(itemDisplay);
                itemDisplay.setItemStack(new ItemStack(Material.STICK));
                itemDisplay.setInterpolationDelay(-1);
                itemDisplay.setInterpolationDuration(100);
                itemDisplay.teleport(endLocation);
            });

            return true;
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
