package net.cyberflame.ancientce.listeners;

import net.cyberflame.ancientce.enums.EnchantTrigger;
import net.cyberflame.ancientce.managers.EnchantManager;
import net.cyberflame.ancientce.utils.ActiveEnchantment;
import net.cyberflame.ancientce.utils.EnchantLevelUtils;
import net.cyberflame.ancientce.utils.Enchantment;
import net.cyberflame.ancientce.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EntityDamageByEntityListener implements Listener
{
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.isCancelled())
            return;
        if (!(e.getEntity() instanceof Player))
            return;
        if (!(e.getDamager() instanceof Player))
            return;
        Player attacker = (Player) e.getDamager();
        Player attacked = (Player) e.getEntity();

        ArrayList<ItemStack> checkItemsAttacker = new ArrayList<>();
        checkItemsAttacker.add(attacker.getInventory().getItemInMainHand());
        checkItemsAttacker.add(attacker.getInventory().getHelmet());
        checkItemsAttacker.add(attacker.getInventory().getChestplate());
        checkItemsAttacker.add(attacker.getInventory().getLeggings());
        checkItemsAttacker.add(attacker.getInventory().getBoots());

        ArrayList<ItemStack> checkItemsAttacked = new ArrayList<>();
        checkItemsAttacked.add(attacked.getInventory().getItemInMainHand());
        checkItemsAttacked.add(attacked.getInventory().getHelmet());
        checkItemsAttacked.add(attacked.getInventory().getChestplate());
        checkItemsAttacked.add(attacked.getInventory().getLeggings());
        checkItemsAttacked.add(attacked.getInventory().getBoots());

        int tier = 1;

        for (ItemStack item : checkItemsAttacker) {
            for (Enchantment enchant : EnchantManager.getEnchantsOnItem(item)) {
                if (!(enchant instanceof ActiveEnchantment))
                    continue;
                ActiveEnchantment ench = (ActiveEnchantment) enchant;
                if (ench.getAffectedItems().contains(item.getType())) {
                    if (ench.getTrigger().equals(EnchantTrigger.onPlayerHit)) {
                        for (String str : item.getItemMeta().getLore()) {
                            if (str.contains(ench.getName())) {
                                tier = EnchantLevelUtils.getEnchantLevelFromString(str.split(" ")[str.split(" ").length - 1]);
                            }
                        }

                        if (Utils.getRandom(1, (int) 100 / ench.getChances().get(tier - 1)) == 1) {
                            ench.doResults(tier, attacker, attacked);
                        }
                    }
                }
            }
        }
    }
}
