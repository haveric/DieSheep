package haveric.dieSheep;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor{

	DieSheep plugin;
	
	static String cmdMain = "diesheep";
	static String cmdHelp = "help";
	
	public Commands(DieSheep wd){
		plugin = wd;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		ChatColor msgColor = ChatColor.DARK_AQUA;
		
		String title = msgColor + "[" + ChatColor.GRAY + plugin.getDescription().getName() + msgColor + "] ";
		
		if (commandLabel.equalsIgnoreCase(cmdMain)){
			if(args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase(cmdHelp))){
				sender.sendMessage(title+"github.com/haveric/DieSheep - v" + plugin.getDescription().getVersion());
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
