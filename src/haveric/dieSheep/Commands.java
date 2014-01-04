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
    static String cmdDrop = "drop";

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

                if (Config.getDropWool()) {
                    sender.sendMessage("Sheep Drop Wool: " + highlightColor + Config.getDropWool());
                } else {
                    sender.sendMessage("Sheep Drop Wool: " + errorColor + Config.getDropWool());
                }


                sender.sendMessage("/" + cmdMain + " " + cmdAddWorld + highlightColor + " world " + defaultColor + "- " + msgColor + "Enables DieSheep on world");
                sender.sendMessage("/" + cmdMain + " " + cmdRemoveWorld + highlightColor + " world " + defaultColor + "- " + msgColor + "Disables DieSheep on world");
                sender.sendMessage("/" + cmdMain + " " + cmdExplosion + highlightColor + " amount " + defaultColor + "- " + msgColor + "Sets the explosion damage");
                sender.sendMessage("/" + cmdMain + " " + cmdDrop + highlightColor + " true" + defaultColor + "/" + highlightColor + "false " + defaultColor + "- " + msgColor + "Sets whether sheep drop wool");
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
                } else if (args[0].equalsIgnoreCase(cmdDrop)) {
                    if (args[1].equalsIgnoreCase("true")) {
                        Config.setDropWool(true);
                        sender.sendMessage("Wool Drops have been " + highlightColor + "enabled");
                    } else if (args[1].equalsIgnoreCase("false")) {
                        Config.setDropWool(false);
                        sender.sendMessage("Wool Drops have been " + errorColor + "disabled");
                    } else {
                        sender.sendMessage(errorColor + "Invalid value. Wool drops can only be set to true or false.");
                    }
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
