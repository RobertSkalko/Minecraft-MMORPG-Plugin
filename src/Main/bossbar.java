package Main;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import net.md_5.bungee.api.ChatColor;


public class bossbar implements Listener{

 public static BossBar bar = Bukkit.createBossBar("Welcome to MMORPG!", BarColor.PURPLE, BarStyle.SEGMENTED_12,BarFlag.CREATE_FOG);
	
 
 @EventHandler
 public static void onPlayerHurt(EntityDamageEvent event){ 		
 	if (event.getEntity() instanceof Player){bossbar.setBossBar((Player) event.getEntity());}
 	
 }
 	
 @EventHandler 
 public void onRegen(EntityRegainHealthEvent event){		
	 if (event.getEntity() instanceof Player){bossbar.setBossBar((Player) event.getEntity());}
 }
 
 
 
	public static void addBossBar(Player player){
	bar.addPlayer(player);	
	}
	
	
	public static void setBossBar(Player player){	
		
	float perc = (float) (player.getHealth() / player.getMaxHealth());
	String hpbar = ChatColor.RED + "" + ChatColor.BOLD + "Health: " + (int)player.getHealth() + "/" + (int) player.getMaxHealth();
			 		 
	bar.setProgress(perc);
	bar.setTitle(hpbar);
	
		
	}
	
	
		
}



