package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.VanillinSMP;
import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand implements CommandExecutor {

    VanillinSMP plugin;

    public MuteChatCommand(VanillinSMP slnsmp) {
        plugin = slnsmp;
    }

    public static boolean chat;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender || (PlayerDataHandler.findData(((Player) sender).getPlayer()).getRank() == PlayerData.Ranks.ADMIN || PlayerDataHandler.findData(((Player) sender).getPlayer()).getRank() == PlayerData.Ranks.STAFF)) {
            Player p = (Player) sender;
            if (chat) {
                Bukkit.broadcastMessage(TC.c("&8[&3!&8] &aChat has been enabled by " + p.getDisplayName()));
                chat = false;
            } else {
                Bukkit.broadcastMessage(TC.c("&8[&3!&8] &bChat has been disabled by " + p.getDisplayName()));
                chat = true;
            }
        } else {
            sender.sendMessage(TC.c("&8[&3!&8] &cYou aren't allowed to do that!"));
        }
        return true;
    }

}
