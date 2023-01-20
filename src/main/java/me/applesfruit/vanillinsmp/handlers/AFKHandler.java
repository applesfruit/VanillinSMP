package me.applesfruit.vanillinsmp.handlers;

import me.applesfruit.vanillinsmp.VanillinSMP;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class AFKHandler implements Listener {

    VanillinSMP plugin;

    public AFKHandler(VanillinSMP slnsmp)
    {
        this.plugin = slnsmp;
    }

    ArrayList<Player> afkList = new ArrayList<>();
    HashMap<Player, Integer> idle = new HashMap<>();
    HashMap<Player, Location> prevLocations = new HashMap<>();

    public void setAFK(Player player)
    {
        afkList.add(player);
        Bukkit.broadcastMessage(TC.c("&8[&3!&8] &b" + player.getDisplayName() + " &3is now AFK."));
        player.sendMessage(TC.c("&8[&3!&8] &3You are now AFK."));
    }

    public boolean getAFK(Player player) { if (afkList.contains(player)) return true; else return false; }

    public void removeAFK(Player player) {
        if (afkList.contains(player)) {
            afkList.remove(player);
            Bukkit.broadcastMessage(TC.c("&8[&3!&8] &b" + player.getDisplayName() + " &3is no longer AFK."));
            player.sendMessage(TC.c("&8[&3!&8] &3You are no longer AFK."));
            idle.remove(player);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        if (afkList.contains(e.getPlayer()))
        {
            removeAFK(e.getPlayer());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        if (afkList.contains(e.getPlayer()))
        {
            removeAFK(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e)
    {
        if (afkList.contains(e.getPlayer()))
        {
            removeAFK(e.getPlayer());
        }
    }

    @EventHandler
    public void onCameraMove(PlayerMoveEvent e)
    {
        if (afkList.contains(e.getPlayer()))
        {
            removeAFK(e.getPlayer());
        }
    }



    public void loop()
    {
        new BukkitRunnable()
        {

            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers())
                {
                    if (isVanished(p))
                        return;
                    Location prev = prevLocations.get(p);

                    if (prev != null)
                    {
                        double distance = prev.distance(p.getLocation());
                        if (distance == 0.0)
                        {
                            if (idle.containsKey(p))
                                idle.put(p, idle.get(p) + 1);
                            else
                                idle.put(p, 1);

                            if (idle.containsKey(p))
                            {
                                if (idle.get(p).intValue() >= 300)
                                    if (!afkList.contains(p))
                                        setAFK(p);
                            }
                        }
                        else
                        {
                            if (getAFK(p))
                            {
                                removeAFK(p);
                            }
                        }
                    }
                    prevLocations.put(p, p.getLocation());
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }


    private boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }


}
