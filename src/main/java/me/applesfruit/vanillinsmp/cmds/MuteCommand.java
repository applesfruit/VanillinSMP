package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            if (args.length >= 1) {
                if (!Bukkit.getPlayer(args[0]).isOnline() || !Bukkit.getPlayer(args[0]).isValid() || Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(TC.c("&8[&3!&8] &cThat player isn't online or doesn't exist!"));
                    return false;
                }

                PlayerDataHandler.mutePlayer(Bukkit.getPlayer(args[0]), Long.MAX_VALUE);
                Bukkit.broadcastMessage(TC.c("&8[&3!&8] &c" + args[0] + "&c has been permanently muted."));
            }
        } else if (sender instanceof Player) {
            Player p = (Player) sender;

            if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF)) {
                if (args.length >= 1) {
                    if (!Bukkit.getPlayer(args[0]).isOnline() || !Bukkit.getPlayer(args[0]).isValid() || Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(TC.c("&8[&3!&8] &cThat player isn't online or doesn't exist!"));
                        return false;
                    }

                    PlayerDataHandler.mutePlayer(Bukkit.getPlayer(args[0]), Long.MAX_VALUE);
                    Bukkit.broadcastMessage(TC.c("&8[&3!&8] &c" + args[0] + "&c has been permanently muted."));
                }
            }

        }

        return true;
    }
}
