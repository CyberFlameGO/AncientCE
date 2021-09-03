package net.cyberflame.ancientce.utils;

import org.bukkit.entity.Player;

import net.cyberflame.ancientce.Main;

public abstract class SubCommand {

	public Main main;
	
    public SubCommand(Main main) {
    	this.main = main;
    }

    public abstract void onCommand(Player player, String[] args);

    public abstract String name();

    public abstract String info();

    public abstract String[] aliases();
}