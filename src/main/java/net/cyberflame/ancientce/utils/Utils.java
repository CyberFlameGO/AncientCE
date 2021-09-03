package net.cyberflame.ancientce.utils;

import org.bukkit.ChatColor;
import java.util.Random;

public class Utils {

	public static int getRandom(int lower, int upper) {
		Random random = new Random();
		return random.nextInt((upper - lower) + 1) + lower;
	}
    
    public static ChatColor enchantColor() {
		return ChatColor.RED;
	}
	
}
