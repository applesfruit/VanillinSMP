package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.VanillinSMP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AFKCommand implements CommandExecutor {

    VanillinSMP plugin;

    public AFKCommand(VanillinSMP slnsmp)
    {
        this.plugin = slnsmp;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player)
        {
            if (plugin.afk.getAFK(((Player) sender).getPlayer()))
                plugin.afk.removeAFK(((Player) sender).getPlayer());
            else
                plugin.afk.setAFK(((Player) sender).getPlayer());
        }

        return true;
    }
}