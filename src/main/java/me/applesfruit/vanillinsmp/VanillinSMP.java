package me.applesfruit.vanillinsmp;

import me.applesfruit.vanillinsmp.cmds.*;
import me.applesfruit.vanillinsmp.handlers.AFKHandler;
import me.applesfruit.vanillinsmp.handlers.ChatHandler;
import me.applesfruit.vanillinsmp.handlers.MessageHandler;
import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillinSMP extends JavaPlugin {
    public static MessageHandler mManager;
    public static AFKHandler afk;

    @Override
    public void onEnable() {
        mManager = new MessageHandler();
        afk = new AFKHandler(this);
        afk.loop();

        PlayerDataHandler.gibPlugin(this);

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(afk, this);
        Bukkit.getPluginManager().registerEvents(new ChatHandler(), this);
        Bukkit.getPluginManager().registerEvents(new ChatColorCommand(), this);

        getCommand("reply").setExecutor(new ReplyCommand(this));
        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("afk").setExecutor(new AFKCommand(this));
        getCommand("mutechat").setExecutor(new MuteChatCommand(this));
        getCommand("clearchat").setExecutor(new ClearChatCommand(this));
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("donate").setExecutor(new DonateCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("about").setExecutor(new AboutCommand());
        getCommand("warn").setExecutor(new WarnCommand());
        getCommand("warns").setExecutor(new WarnsCommand());
        getCommand("chatcolor").setExecutor(new ChatColorCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
