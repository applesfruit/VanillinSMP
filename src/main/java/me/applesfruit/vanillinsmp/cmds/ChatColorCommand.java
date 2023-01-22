package me.applesfruit.vanillinsmp.cmds;

import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ChatColorCommand implements CommandExecutor, Listener {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p)
        {
            ItemStack stainedGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta e = stainedGlass.getItemMeta();
            e.setDisplayName(TC.c("&8"));
            stainedGlass.setItemMeta(e);


            Inventory inv = Bukkit.createInventory(p, 36, TC.c("&3ChatColor GUI"));
            for (int i = 0; i < inv.getSize(); i++)
            {
                inv.setItem(i, stainedGlass);
            }

            inv.setItem(10, createGuiItem(Material.RED_DYE, TC.c("&cRed")));
            inv.setItem(11, createGuiItem(Material.ORANGE_DYE, TC.c("&6Orange/Gold")));
            inv.setItem(12, createGuiItem(Material.YELLOW_DYE, TC.c("&eYellow")));
            inv.setItem(13, createGuiItem(Material.LIME_DYE, TC.c("&aLime/Green")));
            inv.setItem(14, createGuiItem(Material.GREEN_DYE, TC.c("&2Dark Green")));
            inv.setItem(15, createGuiItem(Material.LIGHT_BLUE_DYE, TC.c("&bAqua")));
            inv.setItem(16, createGuiItem(Material.CYAN_DYE, TC.c("&3Cyan")));
            inv.setItem(19, createGuiItem(Material.BLUE_DYE, TC.c("&9Indigo/Blue")));
            inv.setItem(20, createGuiItem(Material.LAPIS_LAZULI, TC.c("&1Dark Blue")));
            inv.setItem(21, createGuiItem(Material.PINK_DYE, TC.c("&dMagenta/Pink")));
            inv.setItem(22, createGuiItem(Material.MAGENTA_DYE, TC.c("&5Purple")));
            inv.setItem(23, createGuiItem(Material.WHITE_DYE, TC.c("&fWhite")));
            inv.setItem(24, createGuiItem(Material.LIGHT_GRAY_DYE, TC.c("&7Gray")));
            inv.setItem(25, createGuiItem(Material.GRAY_DYE, TC.c("&8Dark Gray")));


            p.openInventory(inv);
        }

        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if (e.getView().getTitle().equals(TC.c("&3ChatColor GUI")))
        {
            e.setCancelled(true);
            if (e.getClickedInventory().getHolder() == e.getWhoClicked())
            {
                Player p = (Player) e.getWhoClicked();
                System.out.println("sickk");
                p.playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.2F);
                switch(e.getCurrentItem().getType())
                {
                    case RED_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.RED);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case ORANGE_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.KNOWN_MEMBER))
                            PlayerDataHandler.setChatColor(p, ChatColor.GOLD);
                        else
                            p.sendMessage(TC.c("&cYou need to be Known Member, Trusted, Staff, or Admin to use this!"));
                        break;
                    case YELLOW_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.YELLOW);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case LIME_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.GREEN);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case GREEN_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.DARK_GREEN);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case LIGHT_BLUE_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.AQUA);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case CYAN_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.DARK_AQUA);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case BLUE_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.BLUE);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case LAPIS_LAZULI:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.DARK_BLUE);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case PINK_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.LIGHT_PURPLE);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case MAGENTA_DYE:
                        if (PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.ADMIN) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.STAFF) || PlayerDataHandler.findData(p).getRank().equals(PlayerData.Ranks.TRUSTED))
                            PlayerDataHandler.setChatColor(p, ChatColor.DARK_PURPLE);
                        else
                            p.sendMessage(TC.c("&cYou need to be Trusted, Staff, or Admin to use this!"));
                        break;
                    case WHITE_DYE:
                        PlayerDataHandler.setChatColor(p, ChatColor.WHITE);
                        break;
                    case LIGHT_GRAY_DYE:
                        PlayerDataHandler.setChatColor(p, ChatColor.GRAY);
                        break;
                    case GRAY_DYE:
                        PlayerDataHandler.setChatColor(p, ChatColor.DARK_GRAY);
                        break;

                }
                p.closeInventory();
            }
        }
    }


    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
}
