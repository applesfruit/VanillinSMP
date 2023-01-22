package me.applesfruit.vanillinsmp.handlers;

import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.concurrent.TimeUnit;

import static me.applesfruit.vanillinsmp.cmds.MuteChatCommand.chat;

public class ChatHandler implements Listener {

    @EventHandler
    public void chatFormatting(AsyncPlayerChatEvent e) {
        // mute chat command
        if (chat) {
            if (!PlayerDataHandler.findData(e.getPlayer()).getRank().equals(PlayerData.Ranks.ADMIN) || !PlayerDataHandler.findData(e.getPlayer()).getRank().equals(PlayerData.Ranks.STAFF) || !PlayerDataHandler.findData(e.getPlayer()).getRank().equals(PlayerData.Ranks.RETIRED)) {
                e.setCancelled(true);
                return;
            }
        }

        // muting
        if (PlayerDataHandler.findData(e.getPlayer()).getMuteTime() >= 1) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(TC.c("&cYou are still muted for: " + TimeUnit.SECONDS.toHours(PlayerDataHandler.findData(e.getPlayer()).getMuteTime())));
            return;
        }

        // custom chat formatting w/ ranks & chat color
        e.setCancelled(true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            String finalMessage;
            String formatted;
            if (e.getMessage().toLowerCase().contains(p.getDisplayName().toLowerCase())) {
                formatted = e.getMessage().toLowerCase().replaceAll(p.getDisplayName().toLowerCase(), TC.c("&b" + p.getDisplayName() + PlayerDataHandler.findData(e.getPlayer()).getChatColor()));
                finalMessage = TC.c("&f<" + e.getPlayer().getDisplayName() + "&f> " + PlayerDataHandler.findData(e.getPlayer()).getChatColor() + formatted);

            } else
                finalMessage = TC.c("&f<" + e.getPlayer().getDisplayName() + "&f> &r" + e.getMessage());
            formatted = TC.c(e.getMessage());
            switch (PlayerDataHandler.findData(e.getPlayer()).getRank()) {
                case MEMBER:
                    finalMessage = TC.c(PlayerData.Ranks.MEMBER.prefix + e.getPlayer().getDisplayName() + "&8: " + PlayerDataHandler.findData(e.getPlayer()).getChatColor() + formatted);
                    break;
                case KNOWN_MEMBER:
                    finalMessage = TC.c(PlayerData.Ranks.KNOWN_MEMBER.prefix + e.getPlayer().getDisplayName() + "&8: " + PlayerDataHandler.findData(e.getPlayer()).getChatColor() + formatted);
                    break;
                case RETIRED:
                    finalMessage = TC.c(PlayerData.Ranks.RETIRED.prefix + e.getPlayer().getDisplayName() + "&8: " + PlayerDataHandler.findData(e.getPlayer()).getChatColor() + formatted);
                    break;
                case TRUSTED:
                    finalMessage = TC.c(PlayerData.Ranks.TRUSTED.prefix + e.getPlayer().getDisplayName() + "&8: " + PlayerDataHandler.findData(e.getPlayer()).getChatColor() + formatted);
                    break;
                case STAFF:
                    finalMessage = TC.c(PlayerData.Ranks.STAFF.prefix + e.getPlayer().getDisplayName() + "&8: " + PlayerDataHandler.findData(e.getPlayer()).getChatColor() + formatted);
                    break;
                case ADMIN:
                    finalMessage = TC.c(PlayerData.Ranks.ADMIN.prefix + e.getPlayer().getDisplayName() + "&8: " + PlayerDataHandler.findData(e.getPlayer()).getChatColor() + formatted);
                    break;
            }

            p.sendMessage(finalMessage);
        }
    }
}
