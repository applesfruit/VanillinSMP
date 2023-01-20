package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(TC.c(" "));
        sender.sendMessage(TC.c("&3VanillinSMP Rules"));
        sender.sendMessage(TC.c("&3To maintain a safe and friendly community of Minecraft nerds, we apply these rules:"));
        sender.sendMessage(TC.c("&7- &3Keep spamming to a minimum, use /msg if you want to get someone's attention."));
        sender.sendMessage(TC.c("&7- &3No racist, homophobic, etc. slurs."));
        sender.sendMessage(TC.c("&7- &3Lagging the server any way intentionally isn’t allowed."));
        sender.sendMessage(TC.c("&7- &3Malicious hacking is not allowed. If you have a mod you want to use approve it via asking staff."));
        sender.sendMessage(TC.c("&7- &3Swearing is allowed, don't make it consistent."));
        sender.sendMessage(TC.c("&7- &3Minor griefing is allowed, don't do it at spawn or don't destroy an entire city. dont grief farms, or big projects."));
        sender.sendMessage(TC.c("&7- &3Leaking personal info isn’t allowed."));
        sender.sendMessage(TC.c("&7- &3Use common sense."));
        sender.sendMessage(TC.c(" "));

        return true;
    }
}
