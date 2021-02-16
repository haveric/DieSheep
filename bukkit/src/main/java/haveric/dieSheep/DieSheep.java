package haveric.dieSheep;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DieSheep extends JavaPlugin {
    public Logger log;

    private Commands commands = new Commands(this);

    @Override
    public void onEnable() {
        log = getLogger();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new SheepInteract(), this);

        Config.init(this);

        getCommand(Commands.getMain()).setExecutor(commands);
    }

    @Override
    public void onDisable() {

    }
}
