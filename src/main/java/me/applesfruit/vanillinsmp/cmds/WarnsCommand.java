package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF)) {
                if (!Bukkit.getPlayer(args[0]).isOnline() || !Bukkit.getPlayer(args[0]).isValid() || Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(TC.c("&8[&3!&8] &cThat player isn't online or doesn't exist!"));
                    return false;
                }

                for (int i = 0; i < PlayerDataHandler.findData(Bukkit.getPlayer(args[0])).getWarns().size(); i++) {
                    if (PlayerDataHandler.findData(Bukkit.getPlayer(args[0])).getWarns().isEmpty()) {
                        p.sendMessage(TC.c("&cPlayer has no warns."));
                        break;
                    }
                    p.sendMessage(PlayerDataHandler.findData(Bukkit.getPlayer(args[0])).getWarns().get(i));
                }
            }
        }

        return true;
    }
}
