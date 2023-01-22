package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BanCommand implements CommandExecutor {

    public static ArrayList<String> banList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length >= 2 && (!(sender instanceof Player) || (PlayerDataHandler.findData(((Player) sender).getPlayer())).getRank() == PlayerData.Ranks.ADMIN || PlayerDataHandler.findData(((Player) sender).getPlayer()).getRank() == PlayerData.Ranks.STAFF)) {
            if (Bukkit.getPlayer(args[0]).isOnline() || Bukkit.getPlayer(args[0]) != null) {
                Player targ = Bukkit.getPlayer(args[0]);
                args[0] = "";
                String message = "";
                for (int i = 0; i < args.length; i++) {
                    message += " " + args[i];
                }
                PlayerDataHandler.banPlayer(targ);
                Bukkit.broadcastMessage(TC.c("&8[&3!&8] &c" + targ.getName() + "&c has been banned for: " + message + "&c."));
                return true;
            }

            banList.add(args[0].toLowerCase());
            String target = args[0];
            args[0] = "";
            String message = "";
            for (int i = 0; i < args.length; i++) {
                message += " " + args[i];
            }

            Bukkit.broadcastMessage(TC.c("&8[&3!&8] &c" + target + "&c has been banned for: " + message + "&c."));
        }

        return true;
    }

}
