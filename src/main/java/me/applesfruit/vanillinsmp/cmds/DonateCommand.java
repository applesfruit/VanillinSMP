package me.applesfruit.vanillinsmp.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DonateCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("&3To donate to VanillinSMP, use this link: ");
        sender.sendMessage("&3https://paypal.me/mcaltsus?country.x=US&locale.x=en_US");
        sender.sendMessage("Add your username in the message for a surprise :).");
        return true;
    }
}
