package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.VanillinSMP;
import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    VanillinSMP plugin;

    public ClearChatCommand(VanillinSMP slnsmp) {
        plugin = slnsmp;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((!(sender instanceof Player) || (PlayerDataHandler.findData(((Player) sender).getPlayer())).getRank() == PlayerData.Ranks.ADMIN || PlayerDataHandler.findData(((Player) sender).getPlayer()).getRank() == PlayerData.Ranks.STAFF)) {
            for (int i = 0; i < 500; i++) {
                Bukkit.broadcastMessage(" ");
            }
            Bukkit.broadcastMessage(TC.c("&8[&3!&8] &3Chat cleared by " + ((Player) sender).getDisplayName()));
            return true;
        } else {
            sender.sendMessage(TC.c("&8[&3!&8] &cYou aren't allowed to do that!"));
        }
        return true;
    }
}
