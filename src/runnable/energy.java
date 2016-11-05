package runnable;


import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import combat.stats;
import util.functions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class energy implements Listener{
	
	

	@EventHandler
	void onjoin(PlayerJoinEvent event){
	regainXP(event.getPlayer());
	}	

	
	@EventHandler
	void onSprint(PlayerToggleSprintEvent event){		
		if (currentXP < 1){
		event.setCancelled(true);
		
		Player player = event.getPlayer();		
		player.sendMessage(functions.warningMessage("You're out of ENERGY!"));		
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 55, 2));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 55, 1));		
		player.playSound(player.getLocation(),Sound.BLOCK_ANVIL_HIT, 1, 1);
		
		}		
	}
		
	public static double currentXP = 0;
	static double maxXP = 100;	
	float xpPerc = 0;
	
	
	public static void takeEnergy(Player player){
		
       currentXP -= 10;      
       float xpPerc = (float) currentXP / (float) maxXP;       
       player.setExp(xpPerc);  
       player.setLevel((int) (xpPerc * 100));    	
		
	}
	
    public void regainXP(Player player){

        new BukkitRunnable(){           

            @Override
            public void run(){ 
            	
            if (player.isSprinting()){currentXP -= 3;}        
            
            currentXP += 2 + stats.ENE;             	
            
            if (currentXP > maxXP){currentXP = maxXP;}  
            
            
            xpPerc = (float) (currentXP / maxXP) ;
            player.setExp((float) xpPerc);           	
            player.setLevel((int) (xpPerc * 100)); 
               
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("MMORPG"), 0, 5); //Repeating task with 0 ticks initial delay, run once per 20 ticks (one second). Make sure you pass a valid instance of your plugin.

    }
	
	
	
}