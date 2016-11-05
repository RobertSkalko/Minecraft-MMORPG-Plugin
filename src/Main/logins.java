package Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import combat.statcheck;
import runnable.energy;


public class logins implements Listener{

@EventHandler
public void onLogin(PlayerJoinEvent event){
	
Player player = event.getPlayer();


bossbar.addBossBar(player); 
statcheck.checkStatsAndSet(player);
bossbar.setBossBar(player);



	
    event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "--------------------------------");
    event.getPlayer().sendMessage(ChatColor.GREEN + "Welcome to my MMORPG server!");
    event.getPlayer().sendMessage(ChatColor.GREEN + "Please report bugs and have fun!");
    event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "--------------------------------");    
	 
    
}
}
