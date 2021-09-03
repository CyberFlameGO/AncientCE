package net.cyberflame.ancientce;

import java.util.ArrayList;
import java.util.HashMap;

import net.cyberflame.ancientce.listeners.EntityDamageByEntityListener;
import net.cyberflame.ancientce.listeners.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import net.cyberflame.ancientce.managers.EnchantManager;
import net.cyberflame.ancientce.managers.CommandManager;
import net.cyberflame.ancientce.enums.EnchantTrigger;

public class Main extends JavaPlugin {

	private CommandManager cm;
	private EnchantManager em;

	public void onEnable() {
		cm = new CommandManager(this);
		em = new EnchantManager(this);
		Bukkit.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);


		
		// Chances
		ArrayList<Integer> chances = new ArrayList<>();		
		chances.add(3);
		chances.add(5);
		chances.add(10);
		HashMap<Integer, ArrayList<String>> results = new HashMap<>();

		// Tier one results
		ArrayList<String> one = new ArrayList<>();
		one.add("sendMessage(You have blinded your enemy!) @attacker");
		one.add("sendMessage(You have been blinded!) @attacked");
		one.add("applyPotion(Blindness, 1, 5) @attacked");

		// Tier two results
		ArrayList<String> two = new ArrayList<>();
		two.add("sendMessage(You have blinded your enemy!) @attacker");
		two.add("sendMessage(You have been blinded!) @attacked");
		two.add("applyPotion(Blindness, 1, 10) @attacked");

		// Tier three results
		ArrayList<String> three = new ArrayList<>();
		three.add("sendMessage(You have blinded your enemy!) @attacker");
		three.add("sendMessage(You have been blinded!) @attacked");
		three.add("applyPotion(Blindness, 1, 15) @attacked");

		results.put(1, one);
		results.put(2, two);
		results.put(3, three);

		ArrayList<Material> affectedItems = new ArrayList<>();
		affectedItems.add(Material.WOODEN_SWORD);
		affectedItems.add(Material.STONE_SWORD);
		affectedItems.add(Material.IRON_SWORD);
		affectedItems.add(Material.GOLDEN_SWORD);
		affectedItems.add(Material.DIAMOND_SWORD);
		
		if (!em.enchantExists("Blindness"))
			em.createActiveEnchant("Blindness", "Has a chance to apply blindness to an enemy!", 3, EnchantTrigger.onPlayerHit, chances, results, affectedItems);
		em.loadEnchants();
	}

	public EnchantManager getEnchantManager() {
		return em;
	}

	public CommandManager getCommandManager() {
		return cm;
	}
	
	
	
}
