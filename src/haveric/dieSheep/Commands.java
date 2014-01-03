package haveric.dieSheep;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    DieSheep plugin;

    static String cmdMain = "diesheep";
    static String cmdHelp = "help";
    static String cmdAddWorld = "add";
    static String cmdRemoveWorld = "remove";
    static String cmdExplosion = "explosion";

    public Commands(DieSheep wd) {
        plugin = wd;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ChatColor defaultColor = ChatColor.WHITE;
        ChatColor msgColor = ChatColor.DARK_AQUA;
        ChatColor highlightColor = ChatColor.YELLOW;
        ChatColor errorColor = ChatColor.RED;

        String title = msgColor + "[" + ChatColor.GRAY + plugin.getDescription().getName() + msgColor + "] ";

        if (commandLabel.equalsIgnoreCase(cmdMain)) {
            if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase(cmdHelp))) {
                sender.sendMessage(title + "github.com/haveric/DieSheep - v" + plugin.getDescription().getVersion());

                String worldString = Config.getWorlds();
                if (worldString.equals("")) {
                    worldString = errorColor + "none";
                } else {
                    worldString = highlightColor + worldString;
                }
                sender.sendMessage("Allowed worlds: " + worldString);
                sender.sendMessage("Explosion Damage: " + highlightColor + Config.getExplosionDamage());

                sender.sendMessage("/" + cmdMain + " " + cmdAddWorld + " [world] - " + msgColor + "Enables DieSheep on world");
                sender.sendMessage("/" + cmdMain + " " + cmdRemoveWorld + " [world] - " + msgColor + "Disables DieSheep on world");
                sender.sendMessage("/" + cmdMain + " " + cmdExplosion + " amount - " + msgColor + "Sets the explosion damage");
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase(cmdAddWorld)) {
                    String world = args[1];
                    boolean successful = Config.addWorld(world);

                    if (successful) {
                        sender.sendMessage("World " + highlightColor + world + defaultColor + " added successfully.");
                    } else {
                        sender.sendMessage(errorColor + "World " + highlightColor + world + errorColor + " cannot be added.");
                    }
                } else if (args[0].equalsIgnoreCase(cmdRemoveWorld)) {
                    String world = args[1];
                    boolean successful = Config.removeWorld(world);

                    if (successful) {
                        sender.sendMessage("World " + highlightColor + world + defaultColor + " removed successfully.");
                    } else {
                        sender.sendMessage(errorColor + "World " + highlightColor + world + errorColor + " cannot be removed.");
                    }
                } else if (args[0].equalsIgnoreCase(cmdExplosion)) {
                    double damage = Double.parseDouble(args[1]);
                    Config.setExplosionDamage(damage);
                    sender.sendMessage("Explosion damage set to: " + highlightColor + "damage");
                }
            }
        }
        return false;
    }

    public static String getMain() {
        return cmdMain;
    }

    public static void setMain(String cmd) {
        cmdMain = cmd;
    }

    public static String getHelp() {
        return cmdHelp;
    }

    public static void setHelp(String cmd) {
        cmdHelp = cmd;
    }
}
