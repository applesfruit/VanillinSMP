package me.applesfruit.vanillinsmp.handlers;

import com.google.gson.Gson;
import me.applesfruit.vanillinsmp.VanillinSMP;
import me.applesfruit.vanillinsmp.cmds.UnbanCommand;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerDataHandler {

    private static VanillinSMP slnsmp;

    public static File folder;
    public static File config;

    private static HashMap<Player, PlayerData> dataHashMap = new HashMap<>();

    private static HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();

    public static void gibPlugin(VanillinSMP s) {
        slnsmp = s;
        folder = new File(slnsmp.getDataFolder() + "\\playerdata\\");
        folder.mkdirs();
    }

    public static void create(Player p) {
        config = new File(folder, p.getUniqueId() + ".json");
        if (!config.exists()) {
            PlayerData playerData = new PlayerData(PlayerData.Ranks.MEMBER, ChatColor.GRAY, false, 0, new ArrayList<>() {
            });
            dataHashMap.put(p, playerData);
            save(p);
            loadData(p);
        } else
            loadData(p);
    }

    public static void loadData(Player p) {
        Gson gson = new Gson();
        File file = new File(folder, p.getUniqueId() + ".json");
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                PlayerData d = gson.fromJson(reader, PlayerData.class);
                dataHashMap.put(p, d);
                PermissionAttachment attachment = p.addAttachment(slnsmp);
                perms.put(p.getUniqueId(), attachment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeArrayListPlayerData(Player p) {
        dataHashMap.remove(p);
        perms.remove(p.getUniqueId());
    }

    public static PlayerData findData(Player p) {
        if (dataHashMap.containsKey(p)) {
            return dataHashMap.get(p);
        }
        return null;
    }

    public static void save(Player p) {
        try {
            Gson gson = new Gson();
            File file = new File(folder, p.getUniqueId() + ".json");
            if (!file.exists() || !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Writer writer = new FileWriter(file);
            gson.toJson(findData(p), writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void banPlayer(Player p) {
        PlayerData old = dataHashMap.get(p);
        old.setBanned(true);
        dataHashMap.put(p, old);
        save(p);
        p.kickPlayer(TC.c("&cYou have been permanently banned from VanillinSMP"));
    }

    public static void unbanPlayer(Player p) {
        PlayerData old = dataHashMap.get(p);
        old.setBanned(false);
        dataHashMap.put(p, old);
        save(p);
    }

    public static void mutePlayer(Player p, long minutes) {
        PlayerData old = dataHashMap.get(p);
        old.setMuteTime(minutes);
        dataHashMap.put(p, old);
        save(p);
    }

    public static void unmutePlayer(Player p) {
        PlayerData old = dataHashMap.get(p);
        old.setMuteTime(0);
        dataHashMap.put(p, old);
        save(p);
    }

    public static void warnPlayer(Player p, String reason) {
        PlayerData old = dataHashMap.get(p);
        old.addWarns(reason);
        dataHashMap.put(p, old);
        save(p);
    }

    public static void setChatColor(Player p, ChatColor chatColor)
    {
        PlayerData old = dataHashMap.get(p);
        old.setChatColor(chatColor);
        dataHashMap.put(p, old);
        save(p);
    }


    public static void setRank(Player p, PlayerData.Ranks rank) {
        PlayerData old = dataHashMap.get(p);
        PlayerData.Ranks prevRank = old.getRank();
        old.setRank(rank);
        dataHashMap.put(p, old);
        PermissionAttachment pperms = perms.get(p.getUniqueId());
        pperms.setPermission(rank.permission, true);
        perms.get(p.getUniqueId()).unsetPermission(prevRank.permission);
        save(p);

    }


}
