package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AboutCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(TC.c(" "));
            sender.sendMessage(TC.c("&3VanillinSMP is a SMP focused on technical Minecraft and having fun."));
            sender.sendMessage(TC.c("&3We want our players to enjoy the experience of an uninterrupted Minecraft SMP, with little to no moderator interference."));
            sender.sendMessage(TC.c("&3VanillinSMP is a free server, you are able to do whatever you want without any payment, but with this, comes costs on our end."));
            sender.sendMessage(TC.c("&3If you would like to support our server, use: /donate"));
            sender.sendMessage(TC.c("&3We hope you enjoy playing on VanillinSMP"));
            sender.sendMessage(TC.c("&3Type /about ranks to view how our rank system works."));
            sender.sendMessage(TC.c(" "));
        }
        else if (args[0].equalsIgnoreCase("ranks"))
        {
            sender.sendMessage(TC.c(" "));
            sender.sendMessage(TC.c(PlayerData.Ranks.MEMBER.prefix + "&7Player &8- &3The default rank, when you join the server, you get this rank."));
            sender.sendMessage(TC.c(PlayerData.Ranks.KNOWN_MEMBER.prefix + "&6Player &8- &3The Known Member rank, when you've consistently played for atleast a week, you get this."));
            sender.sendMessage(TC.c(PlayerData.Ranks.TRUSTED.prefix + "&ePlayer &8- &3The Trusted rank, when you've consistently played for atleast a month, you get this. HAVE TO BE TRUSTWORTHY."));
            sender.sendMessage(TC.c(PlayerData.Ranks.RETIRED.prefix + "&9Player &8- &3The Retired rank, when you've retired your position of staff."));
            sender.sendMessage(TC.c(PlayerData.Ranks.STAFF.prefix + "&bPlayer &8- &3The Staff rank, when you are a staff member that helps with moderation."));
            sender.sendMessage(TC.c(PlayerData.Ranks.ADMIN.prefix + "&3Player &8- &3The Admin rank, you can't get this. We manage the server."));
            sender.sendMessage(TC.c("&3Type /about punishments to view how our punishment system works."));
            sender.sendMessage(TC.c(" "));
        }
        else if (args[0].equalsIgnoreCase("punishments"))
        {
            sender.sendMessage(TC.c(" "));
            sender.sendMessage(TC.c("&3When you break our rules, we are pretty tolerant, until we're not."));
            sender.sendMessage(TC.c("&3Most punishments will be permanent bans, that's only if you're a bad person, if not then you don't have to worry about anything."));
            sender.sendMessage(TC.c("&3View /rules on a in-depth explanation on what rules to follow."));
            sender.sendMessage(TC.c(" "));
        }

        return true;
    }
}
