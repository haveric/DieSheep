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

    // Old explosion type used to convert older plugin versions
    private static String cfgExplosionDamage = "ExplosionDamage";
    private static String cfgDropWool = "DropWool";

    private static String cfgShearType = "ShearType";
    private static String cfgShearExplosionPower = "ShearExplosionPower";
    private static String cfgDieType = "DieType";
    private static String cfgDieExplosionPower = "DieExplosionPower";
    private static String cfgDyeType = "DyeType";
    private static String cfgDyeExplosionPower = "DyeExplosionPower";

    private static final boolean DROP_WOOL_DEFAULT = true;
    private static final double DEFAULT_EXPLOSION_POWER = 1.2;
    public static final String TYPE_WOLF = "wolf";
    public static final String TYPE_NONE = "none";
    public static final String TYPE_EXPLOSION = "explosion";


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

        // Handle old versions of the plugin by moving the explosion damage.
        double dmg = config.getDouble(cfgExplosionDamage, -1);
        if (dmg != -1) {
            config.set(cfgDieExplosionPower, dmg);
            config.set(cfgDyeExplosionPower, dmg);
            config.set(cfgExplosionDamage, null);
        }

        config.addDefault(cfgDropWool, DROP_WOOL_DEFAULT);
        config.addDefault(cfgShearType, TYPE_WOLF);
        config.addDefault(cfgShearExplosionPower, DEFAULT_EXPLOSION_POWER);
        config.addDefault(cfgDieType, TYPE_EXPLOSION);
        config.addDefault(cfgDieExplosionPower, DEFAULT_EXPLOSION_POWER);
        config.addDefault(cfgDyeType, TYPE_EXPLOSION);
        config.addDefault(cfgDyeExplosionPower, DEFAULT_EXPLOSION_POWER);

        if (!config.isSet(cfgDropWool) || !config.isSet(cfgShearType) || !config.isSet(cfgShearExplosionPower)
         || !config.isSet(cfgDieType) || !config.isSet(cfgDieExplosionPower) || !config.isSet(cfgDyeType) || !config.isSet(cfgDyeExplosionPower)) {
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

    public static void setShearType(String type) {
        if (type.equals("wolf") || type.equals("none") || type.equals("explosion")) {
            config.set(cfgShearType, type);
            saveConfig();
        }
    }

    public static String getShearType() {
        return config.getString(cfgShearType);
    }

    public static void setShearExplosionPower(double newPower) {
        if (newPower >= 0) {
            config.set(cfgShearExplosionPower, newPower);
            saveConfig();
        }
    }

    public static double getShearExplosionPower() {
        return config.getDouble(cfgShearExplosionPower, DEFAULT_EXPLOSION_POWER);
    }

    public static void setDieType(String type) {
        if (type.equals("none") || type.equals("explosion")) {
            config.set(cfgDieType, type);
            saveConfig();
        }
    }

    public static String getDieType() {
        return config.getString(cfgDieType);
    }

    public static void setDieExplosionPower(double newPower) {
        if (newPower >= 0) {
            config.set(cfgDieExplosionPower, newPower);
            saveConfig();
        }
    }

    public static double getDieExplosionPower() {
        return config.getDouble(cfgDieExplosionPower, DEFAULT_EXPLOSION_POWER);
    }

    public static void setDyeType(String type) {
        if (type.equals("none") || type.equals("explosion")) {
            config.set(cfgDyeType, type);
            saveConfig();
        }
    }

    public static String getDyeType() {
        return config.getString(cfgDyeType);
    }

    public static void setDyeExplosionPower(double newPower) {
        if (newPower >= 0) {
            config.set(cfgDyeExplosionPower, newPower);
            saveConfig();
        }
    }

    public static double getDyeExplosionPower() {
        return config.getDouble(cfgDyeExplosionPower, DEFAULT_EXPLOSION_POWER);
    }
}
