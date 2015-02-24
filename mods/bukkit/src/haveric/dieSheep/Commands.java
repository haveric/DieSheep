package haveric.dieSheep;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    private DieSheep plugin;

    private static String cmdMain = "diesheep";
    private static String cmdHelp = "help";
    private static String cmdAddWorld = "add";
    private static String cmdRemoveWorld = "remove";
    private static String cmdShearExplosion = "shear";
    private static String cmdDieExplosion = "die";
    private static String cmdDyeExplosion = "dye";
    private static String cmdDrop = "drop";

    public Commands(DieSheep wd) {
        plugin = wd;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ChatColor defaultColor = ChatColor.WHITE;
        ChatColor msgColor = ChatColor.DARK_AQUA;
        ChatColor highlightColor = ChatColor.GOLD;
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

                String dropTrueFalse;
                if (Config.getDropWool()) {
                    dropTrueFalse = " <" + highlightColor + "true" + defaultColor + ",false>";
                } else {
                    dropTrueFalse =  " <true," + highlightColor + "false" + defaultColor + ">";
                }

                double shearExplosionPower = Math.round(Config.getShearExplosionPower() * 100) / 100;
                double dieExplosionPower = Math.round(Config.getDieExplosionPower() * 100) / 100;
                double dyeExplosionPower = Math.round(Config.getDyeExplosionPower() * 100) / 100;


                sender.sendMessage("/" + cmdMain + " " + cmdAddWorld +  " <world> - " + msgColor + "Enables DieSheep on world");
                sender.sendMessage("/" + cmdMain + " " + cmdRemoveWorld + " <world> - " + msgColor + "Disables DieSheep on world");
                sender.sendMessage("/" + cmdMain + " " + cmdShearExplosion + " <amount> " + highlightColor + shearExplosionPower +  defaultColor + " - " + msgColor + "Sets shear explosion power");
                sender.sendMessage("/" + cmdMain + " " + cmdDieExplosion + " <amount> " + highlightColor + dieExplosionPower + defaultColor + " - " + msgColor + "Sets die explosion power");
                sender.sendMessage("/" + cmdMain + " " + cmdDyeExplosion + " <amount> " + highlightColor + dyeExplosionPower + defaultColor + " - " + msgColor + "Sets dye explosion power");
                sender.sendMessage("/" + cmdMain + " " + cmdDrop + dropTrueFalse + " - " + msgColor + "Sets whether sheep drop wool");
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
                } else if (args[0].equalsIgnoreCase(cmdShearExplosion)) {
                    double power = Double.parseDouble(args[1]);
                    Config.setShearExplosionPower(power);
                    sender.sendMessage("Shear Explosion power set to: " + highlightColor + "power");
                } else if (args[0].equalsIgnoreCase(cmdDieExplosion)) {
                    double power = Double.parseDouble(args[1]);
                    Config.setDieExplosionPower(power);
                    sender.sendMessage("Die Explosion power set to: " + highlightColor + "power");
                } else if (args[0].equalsIgnoreCase(cmdDyeExplosion)) {
                    double power = Double.parseDouble(args[1]);
                    Config.setDyeExplosionPower(power);
                    sender.sendMessage("Dye Explosion power set to: " + highlightColor + "power");
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
