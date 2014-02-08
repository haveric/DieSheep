package haveric.dieSheep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

    private static DieSheep plugin;

    private static ArrayList<String> worlds;
    private static String cfgAllowedWorlds = "AllowedWorlds";
    private static String cfgExplosionDamage = "ExplosionDamage";
    private static String cfgDropWool = "DropWool";

    private static final boolean DROP_WOOL_DEFAULT = true;
    private static final double DEFAULT_EXPLOSION_DAMAGE = 1.2;

    private static FileConfiguration config;
    private static File configFile;


    /**
     * Initializes the config file
     * @param ss The main class used to
     */
    public static void init(DieSheep ds) {
        plugin = ds;
        configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        setup();
    }

    /**
     * Sets up the default variables if they don't exist yet.
     *
     */
    @SuppressWarnings("unchecked")
    public static void setup() {
        worlds = (ArrayList<String>) config.get(cfgAllowedWorlds);

        if (worlds == null) {
            worlds = new ArrayList<String>();
            worlds.add("testworld");
            worlds.add("testworld2");
            config.set(cfgAllowedWorlds, worlds);
        }

        double dmg = config.getDouble(cfgExplosionDamage, -1);
        if (dmg == -1) {
            config.set(cfgExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
        }

        config.addDefault(cfgDropWool, DROP_WOOL_DEFAULT);

        if (!config.isSet(cfgDropWool)) {
            config.options().copyDefaults(true);
        }
        saveConfig();
    }

    /**
     * Saves the configuration to the file.
     */
    private static void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isEnabled(String world) {
        boolean enabled = false;

        if (worlds.contains(world)) {
            enabled = true;
        }

        return enabled;
    }

    public static boolean addWorld(String world) {
        boolean successful = false;

        if (!worlds.contains(world)) {
            worlds.add(world);
            config.set(cfgAllowedWorlds, worlds);
            saveConfig();
            successful = true;
        }

        return successful;
    }

    public static boolean removeWorld(String world) {
        boolean successful = false;

        if (worlds.contains(world)) {
            worlds.remove(world);
            config.set(cfgAllowedWorlds, worlds);
            saveConfig();
            successful = true;
        }
        return successful;
    }

    public static void setExplosionDamage(double newDamage) {
        if (newDamage >= 0) {
            config.set(cfgExplosionDamage, newDamage);
            saveConfig();
        }
    }

    public static float getExplosionDamage() {
        return (float) config.getDouble(cfgExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
    }

    public static void setDropWool(boolean canDrop) {
        config.set(cfgDropWool, canDrop);
        saveConfig();
    }

    public static boolean getDropWool() {
        return config.getBoolean(cfgDropWool);
    }

    public static String getWorlds() {
        String worldString = "";

        int worldLength = worlds.size();
        for (int i = 0; i < worldLength; i++) {
            worldString += worlds.get(i);
            if (i < worldLength - 1) {
                worldString += " ";
            }
        }

        return worldString;
    }
}
