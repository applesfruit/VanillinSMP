package me.applesfruit.vanillinsmp.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {

    private ChatColor chatColor;
    private Ranks rank;
    private boolean banned;
    private long muteTime;
    private ArrayList<String> warns;

    public PlayerData(Ranks rank, ChatColor chatColor, boolean banned, long muteTime, ArrayList<String> warns) {
        this.rank = rank;
        this.chatColor = chatColor;
        this.banned = banned;
        this.muteTime = muteTime;
        this.warns = warns;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public void setChatColor(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public long getMuteTime() {
        return muteTime;
    }

    public void setMuteTime(long muteTime) {
        this.muteTime = muteTime;
    }

    public List<String> getWarns() {
        return warns;
    }

    public void setWarns(ArrayList<String> warns) {
        this.warns = warns;
    }

    public void addWarns(String warn) {
        this.warns.add(warn);
    }

    public enum Ranks {
        MEMBER("group.member", TC.c("&7[M] &7")),
        KNOWN_MEMBER("group.known_member", TC.c("&7[&6K&7] &6")),
        TRUSTED("group.trusted", TC.c("&7[&eT&7] &e")),
        RETIRED("group.retired", TC.c("&7[&9R&7] &9")),
        STAFF("group.staff", TC.c("&7[&bS&7] &b")),
        ADMIN("group.admin", TC.c("&7[&3A&7] &3"));

        public String permission;
        public String prefix;

        Ranks(String permission, String prefix) {
            this.permission = permission;
            this.prefix = prefix;
        }

    }
}
