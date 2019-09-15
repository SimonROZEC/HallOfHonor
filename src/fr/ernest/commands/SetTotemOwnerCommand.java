package fr.ernest.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.ernest.hoh.HallOfHonor;
import fr.ernest.hoh.entities.AbstractTotem;
import net.md_5.bungee.api.ChatColor;

public class SetTotemOwnerCommand implements CommandExecutor {

	public static final String NAME = "settotemowner";

	private HallOfHonor plugin = HallOfHonor.getPlugin(HallOfHonor.class);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;

		if (args.length != 2)
			return false;

		String name = args[0];

		Player player = Bukkit.getPlayer(args[1]);

		if (plugin.getTotemsManager().totemExists(name) && player != null) {
			AbstractTotem totem = plugin.getTotemsManager().getTotem(name);
			totem.setOwner(player);
			totem.save(plugin.getStoreManager().getStore("totems"));
			plugin.getStoreManager().saveStore("totems");
			plugin.getServer().broadcastMessage("" + totem);
			return true;
		}
		return false;
	}

}