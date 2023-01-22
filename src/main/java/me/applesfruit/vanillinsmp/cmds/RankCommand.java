package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length >= 2 && (!(sender instanceof Player) || (PlayerDataHandler.findData(((Player) sender).getPlayer())).getRank() == PlayerData.Ranks.ADMIN || PlayerDataHandler.findData(((Player) sender).getPlayer()).getRank() == PlayerData.Ranks.STAFF)) {
            if (!Bukkit.getPlayer(args[0]).isOnline() || !Bukkit.getPlayer(args[0]).isValid() || Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(TC.c("&8[&3!&8] &cThat player isn't online or doesn't exist!"));
                return false;
            }
            Player p = Bukkit.getPlayer(args[0]);

            switch (args[1].toLowerCase()) {
                case "member":
                    PlayerDataHandler.setRank(p, PlayerData.Ranks.MEMBER);
                    break;
                case "known":
                    PlayerDataHandler.setRank(p, PlayerData.Ranks.KNOWN_MEMBER);
                    break;
                case "trusted":
                    PlayerDataHandler.setRank(p, PlayerData.Ranks.TRUSTED);
                    break;
                case "retired":
                    PlayerDataHandler.setRank(p, PlayerData.Ranks.RETIRED);
                    break;
                case "staff":
                    PlayerDataHandler.setRank(p, PlayerData.Ranks.STAFF);
                    break;
                case "admin":
                    PlayerDataHandler.setRank(p, PlayerData.Ranks.ADMIN);
                    break;
            }

            p.sendMessage(TC.c("&8[&3!&8] &3Your rank has been updated to: &b" + PlayerDataHandler.findData(p).getRank().name()));
            sender.sendMessage(TC.c("&8[&3!&8] &3Sucessfully updated &b" + p.getName() + "&b's &3rank to &b" + PlayerDataHandler.findData(p).getRank().name()));
        }

        return true;
    }
}
