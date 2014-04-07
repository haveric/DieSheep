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
    @Deprecated
    private static String cfgExplosionDamage = "ExplosionDamage";
    private static String cfgDropWool = "DropWool";

    private static String cfgShearType = "ShearType";
    private static String cfgShearExplosionDamage = "ShearExplosionDamage";
    private static String cfgDieType = "DieType";
    private static String cfgDieExplosionDamage = "DieExplosionDamage";
    private static String cfgDyeType = "DyeType";
    private static String cfgDyeExplosionDamage = "DyeExplosionDamage";

    private static final boolean DROP_WOOL_DEFAULT = true;
    private static final double DEFAULT_EXPLOSION_DAMAGE = 1.2;
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
            config.set(cfgDieExplosionDamage, dmg);
            config.set(cfgDyeExplosionDamage, dmg);
            config.set(cfgExplosionDamage, null);
        }

        config.addDefault(cfgDropWool, DROP_WOOL_DEFAULT);
        config.addDefault(cfgShearType, TYPE_WOLF);
        config.addDefault(cfgShearExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
        config.addDefault(cfgDieType, TYPE_EXPLOSION);
        config.addDefault(cfgDieExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
        config.addDefault(cfgDyeType, TYPE_EXPLOSION);
        config.addDefault(cfgDyeExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);

        if (!config.isSet(cfgDropWool) || !config.isSet(cfgShearType) || !config.isSet(cfgShearExplosionDamage)
         || !config.isSet(cfgDieType) || !config.isSet(cfgDieExplosionDamage) || !config.isSet(cfgDyeType) || !config.isSet(cfgDyeExplosionDamage)) {
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

    @Deprecated
    public static void setExplosionDamage(double newDamage) {
        if (newDamage >= 0) {
            config.set(cfgExplosionDamage, newDamage);
            saveConfig();
        }
    }
    @Deprecated
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

    public static void setShearType(String type) {
        if (type.equals("wolf") || type.equals("none") || type.equals("explosion")) {
            config.set(cfgShearType, type);
            saveConfig();
        }
    }

    public static String getShearType() {
        return config.getString(cfgShearType);
    }

    public static void setShearExplosionDamage(double newDamage) {
        if (newDamage >= 0) {
            config.set(cfgShearExplosionDamage, newDamage);
            saveConfig();
        }
    }

    public static double getShearExplosionDamage() {
        return config.getDouble(cfgShearExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
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

    public static void setDieExplosionDamage(double newDamage) {
        if (newDamage >= 0) {
            config.set(cfgDieExplosionDamage, newDamage);
            saveConfig();
        }
    }

    public static double getDieExplosionDamage() {
        return config.getDouble(cfgDieExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
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

    public static void setDyeExplosionDamage(double newDamage) {
        if (newDamage >= 0) {
            config.set(cfgDyeExplosionDamage, newDamage);
            saveConfig();
        }
    }

    public static double getDyeExplosionDamage() {
        return config.getDouble(cfgDyeExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
    }
}
