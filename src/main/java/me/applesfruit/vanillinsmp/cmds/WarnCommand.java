package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length >= 2) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF)) {
                    if (!Bukkit.getPlayer(args[0]).isOnline() || !Bukkit.getPlayer(args[0]).isValid() || Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(TC.c("&8[&3!&8] &cThat player isn't online or doesn't exist!"));
                        return false;
                    }

                    Player target = Bukkit.getPlayer(args[0]);

                    String message = "";
                    args[0] = "";
                    for (int i = 0; i < args.length; i++) {
                        message += " " + args[i];
                    }

                    PlayerDataHandler.warnPlayer(target, message);
                    Bukkit.broadcastMessage(TC.c("&8[&3!&8] &c" + target.getName() + "&c has been warned for: &c" + message));
                }
            }
        }
        return true;
    }
}
