package me.applesfruit.vanillinsmp.handlers;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageHandler {

    // arraylists

    HashMap<Player, Player> convos = new HashMap<>();

    public void setReplyTarget(Player messager, Player receiver) {
        convos.put(messager, receiver);
        convos.put(receiver, messager);
    }

    public Player getReplyTarget(Player messager) {
        if (convos.containsKey(messager))
            return convos.get(messager);
        return null;
    }

    public void remove(Player player) {
        convos.remove(convos.get(player));
        convos.remove(player);
    }


}
