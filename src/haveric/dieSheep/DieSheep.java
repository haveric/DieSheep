package haveric.dieSheep;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DieSheep extends JavaPlugin {

    private Commands commands = new Commands(this);

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new SheepInteract(this), this);

        Config.init(this);

        getCommand(Commands.getMain()).setExecutor(commands);
    }

    @Override
    public void onDisable() {

    }
}
