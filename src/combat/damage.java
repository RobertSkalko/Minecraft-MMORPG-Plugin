package combat;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import util.functions;
import combat.statcheck;
import runnable.energy;

public class damage extends stats implements Listener{

	
@EventHandler
public void onEntityTakeDMG(EntityDamageByEntityEvent event){
	
	Player player = null;
	Entity mob = null;	
	if (event.getEntity() instanceof Entity) mob = event.getEntity();	
	if (event.getDamager() instanceof Player) player = (Player) event.getDamager();
			
	if (player != null && mob != null){
		
	if (energy.currentXP < 0.1){
		event.setDamage(0);
		player.sendMessage(functions.warningMessage("You're out of ENERGY!"));
		
		PotionEffect slow = (new PotionEffect(PotionEffectType.SLOW, 55, 2));
		PotionEffect mining = (new PotionEffect(PotionEffectType.SLOW_DIGGING, 55, 1));
		
		player.addPotionEffect(slow);
		player.addPotionEffect(mining);
		
		player.playSound(player.getLocation(),Sound.BLOCK_ANVIL_HIT, 1, 1);
		
		return;
	}
		
	statcheck.onDamageCheckWeaponStats(player);	
	
	energy.takeEnergy(player);

	double totalFIREDMG = FIREDMG + FIREDMG * FIREDMGBONUS / 100;
	double totalICEDMG = ICEDMG + ICEDMG * ICEDMGBONUS / 100;
	double totalPUREDMG = PUREDMG + PUREDMG * PUREDMGBONUS / 100;
	double totalPOISONDMG = POISONDMG + POISONDMG * POISONDMGBONUS / 100;
		
	double lifestolen = 0;
	double totalDMG = (DMG + totalFIREDMG + totalICEDMG + totalPOISONDMG + totalPUREDMG);
	totalDMG += totalDMG * PVEDMG / 100;
	
	if (LIFESTEAL > 0) {lifestolen =  totalDMG * LIFESTEAL / 100;}		
	if (functions.roll(CRITCHANCE)){totalDMG = totalDMG + (totalDMG * CRITDMG / 100);player.sendMessage(functions.succeedMessage("Critical!"));}
				
	if (player.getHealth() + lifestolen> player.getMaxHealth()){player.setHealth(player.getMaxHealth());}
	else player.setHealth(player.getHealth() + lifestolen);
		
		
		event.setDamage(totalDMG);	
		
	}
	
}



		
		
	
}
