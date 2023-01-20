package me.applesfruit.vanillinsmp.cmds;

import com.google.gson.Gson;
import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;

public class UnbanCommand implements CommandExecutor {

    public static ArrayList<String> unbanList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 && (!(sender instanceof Player) || (PlayerDataHandler.findData(((Player) sender).getPlayer())).getRank() == PlayerData.Ranks.ADMIN || PlayerDataHandler.findData(((Player) sender).getPlayer()).getRank() == PlayerData.Ranks.STAFF))
        {
            unbanList.add(args[0].toLowerCase());
            sender.sendMessage(TC.c("&8[&a!&8] &a" + args[0] + "&a has been unbanned."));
        }

        return true;
    }
}
