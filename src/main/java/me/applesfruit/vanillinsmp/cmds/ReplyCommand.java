package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.VanillinSMP;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class ReplyCommand implements CommandExecutor {

    VanillinSMP plugin;

    public ReplyCommand(VanillinSMP slnsmp)
    {
        plugin = slnsmp;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && args.length > 0)
        {
            Player messager = (Player) sender;
            if (plugin.mManager.getReplyTarget(messager) != null) {

                if (!plugin.mManager.getReplyTarget(messager).isOnline() || !plugin.mManager.getReplyTarget(messager).isValid() || isVanished(plugin.mManager.getReplyTarget(messager)))
                {
                    messager.sendMessage(TC.c("&8[&3!&8] &cThat player isn't online or doesn't exist!"));
                    return false;
                }

                String message = "";
                for (int i = 0; i < args.length; i++)
                {
                    message += " " + args[i];
                }
                if (message.isEmpty() || message.isBlank()) {
                    messager.sendMessage(TC.c("&8[&3!&8] &cEnter a message!"));
                    return false;
                }
                if (plugin.afk.getAFK(plugin.mManager.getReplyTarget(messager)))
                    messager.sendMessage(TC.c("&8[&3!&8] &b" + plugin.mManager.getReplyTarget(messager).getDisplayName() + " &3is currently AFK and may not respond."));
                messager.sendMessage(TC.c("&8[&3!&8] &3To &b" + plugin.mManager.getReplyTarget(messager).getDisplayName() + " &8➤ &7" + message));
                plugin.mManager.getReplyTarget(messager).sendMessage(TC.c("&8[&3!&8] &3From &b" + messager.getDisplayName() + " &8➤ &7" + message));
                plugin.mManager.getReplyTarget(messager).playSound(plugin.mManager.getReplyTarget(messager).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.2F);
            }
            else
            {
                messager.sendMessage(TC.c("&8[&3!&8] &cYou aren't in a conversation with anyone!"));
            }

        }

        return true;
    }

    private boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }
}
