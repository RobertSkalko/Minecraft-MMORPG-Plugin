package combat;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import util.functions;


public class takedamage extends stats implements Listener{

	
@EventHandler
public void onPlayerTakeDMG(EntityDamageEvent event){
	
	Player player = null;
	
	if (event.getEntity() instanceof Player){
	if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
		event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE ){
		
	player = (Player) event.getEntity();			
		
	player.sendMessage("starting dmg: "+ event.getDamage());
	double damage = event.getDamage();	
	
	if (functions.roll(HEALCHANCE)){
		
		if (player.getHealth()+(player.getHealth()*0.1) < player.getMaxHealth()){
		player.setHealth(player.getHealth()+(player.getHealth()*0.1));
		}
		else {player.setHealth(player.getMaxHealth());
		}
	}
		
	if (ARMOR > 0){damage -= damage * ARMOR / 100;}	
	if (functions.roll(DODGE)){damage = 0; player.sendMessage(functions.succeedMessage("Dodged!"));event.setDamage(damage);return;}
	if (functions.roll(BLOCK)){damage /= 2; player.sendMessage(functions.succeedMessage("Blocked!"));event.setDamage(damage);return;}	
	if (functions.roll(REFLECT)){damage = 0; player.sendMessage(functions.succeedMessage("Reflected!"));event.setDamage(damage);return;}
	player.sendMessage(" dmg: "+ damage);
	event.setDamage(damage);
		
	}}
		
	
	
}
		

@EventHandler
public void onEnvironmentDMG(EntityDamageEvent event){
	if (event.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR && event.getEntity() instanceof Player){
		
		Player player = (Player) event.getEntity();
		
		event.setDamage(player.getMaxHealth() / 10);
	}
	
}
		
		
	
}
