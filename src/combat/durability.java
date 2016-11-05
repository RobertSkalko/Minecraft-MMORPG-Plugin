package combat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import util.functions;

public class durability implements Listener {

	


@EventHandler
public void onItemDamage(PlayerItemDamageEvent event){
	
	Player player = event.getPlayer();
	
	
	ItemStack item = event.getItem();	
	ItemMeta meta = item.getItemMeta();
	List<String> lores = meta.getLore();
	
	String durability = functions.giveLoreEquals(lores,"Durability");
	int duraLore = functions.loreEquals(lores,"Durability");
	
	int firstNumber = functions.getFirstNumber(durability);
	int secondNumber = functions.getSecondNumber(durability);
	firstNumber--;
	
	lores.set(duraLore, ChatColor.GRAY + "Durability: " + firstNumber + "/" + secondNumber);
	
	meta.setLore(lores);
	item.setItemMeta(meta);
	
	
	double maxDura = item.getType().getMaxDurability();
	
	double ratio = maxDura / secondNumber;	
	
	double setAmount = (secondNumber - firstNumber) * ratio;
		
	item.setDurability((short) setAmount);
	
}

	
	
}
