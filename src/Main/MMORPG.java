package Main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import Main.logins;
import Menus.CHARACTER;
import Menus.VENDORS;
import classes.DEATHPENALTY;
import classes.ENCHANTING;
import classes.IDENTIFICATION;
import classes.MINING;
import classes.POWDERS;
import classes.REPAIR;
import classes.REROLL;
import combat.damage;
import combat.durability;
import combat.statcheck;
import combat.takedamage;
import database.items;
import runnable.energy;
import runnable.regen;
import Main.bossbar;

public class MMORPG extends JavaPlugin implements Listener {

	public void onEnable() {
		items.main();
		PluginManager pm = getServer().getPluginManager();

		// add new listeners here
		pm.registerEvents(new listeners(), this);
		pm.registerEvents(new statcheck(), this);
		pm.registerEvents(new damage(), this);
		pm.registerEvents(new regen(), this);
		pm.registerEvents(new durability(), this);
		pm.registerEvents(new takedamage(), this);
		pm.registerEvents(new logins(), this);
		pm.registerEvents(new bossbar(), this);
		pm.registerEvents(new CHARACTER(), this);
		pm.registerEvents(new energy(), this);
		pm.registerEvents(new VENDORS(), this);
		pm.registerEvents(new POWDERS(), this);
		pm.registerEvents(new REPAIR(), this);
		pm.registerEvents(new REROLL(), this);
		pm.registerEvents(new DEATHPENALTY(), this);
		pm.registerEvents(new ENCHANTING(), this);
		pm.registerEvents(new IDENTIFICATION(), this);
		pm.registerEvents(new MINING(), this);
	}

	

} // class
