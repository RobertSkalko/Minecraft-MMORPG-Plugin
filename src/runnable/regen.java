package runnable;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import Main.bossbar;
import combat.stats;

public class regen extends stats implements Listener {

	

@EventHandler
void regainHealth(PlayerJoinEvent event){	
	
	Player player = event.getPlayer();
	
    new BukkitRunnable(){           

        @Override
        public void run(){         	
        	
        if (player.getFoodLevel() < 10){return;}
        
        double regainedHP = player.getHealth() + stats.HPREGEN;
        
        if (regainedHP > player.getMaxHealth()){regainedHP = player.getMaxHealth();}
        
        bossbar.setBossBar(player);
        
        player.setHealth(regainedHP);       

        }
        
    }.runTaskTimer(Bukkit.getPluginManager().getPlugin("MMORPG"), 0, 50); 

}

	
}
