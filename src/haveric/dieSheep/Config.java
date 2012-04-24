package haveric.dieSheep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	static DieSheep plugin;

	static ArrayList<String> worlds;
	private static String cfgAllowedWorlds = "AllowedWorlds";
	private static String cfgExplosionDamage = "ExplosionDamage";
	
	private static final double DEFAULT_EXPLOSION_DAMAGE = 1.2;
	
    public static FileConfiguration config;
    public static File configFile;
    

    /**
     * Initializes the config file
     * @param ss The main class used to 
     */
    public static void init(DieSheep ds){
    	plugin = ds;
    	configFile = new File(plugin.getDataFolder() + "/config.yml");
    	config = YamlConfiguration.loadConfiguration(configFile);
    	setup();
    }
    
    /** 
     * Sets up the default variables if they don't exist yet.
     * 
     */
    @SuppressWarnings("unchecked")
	public static void setup(){
    	worlds = (ArrayList<String>) config.get(cfgAllowedWorlds);
    	
    	if (worlds == null){
    		worlds = new ArrayList<String>();
    		worlds.add("testworld");
        	worlds.add("testworld2");
        	config.set(cfgAllowedWorlds, worlds);
    	}
    	
    	double dmg = config.getDouble(cfgExplosionDamage, -1);
    	if (dmg == -1){
    		config.set(cfgExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
    	}
    	saveConfig();
    }
    
    /**
     * Saves the configuration to the file.
     */
	private static void saveConfig(){
		try {
			config.save(configFile);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static boolean isEnabled(String world){
		boolean enabled = false;
		
		if (worlds.contains(world)){
			enabled = true;
		}
		
		return enabled;
	}
	
	public static float getExplosionDamage(){
		return (float) config.getDouble(cfgExplosionDamage, DEFAULT_EXPLOSION_DAMAGE);
	}
}
