package combat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import util.functions;

public class statcheck extends stats implements Listener {
static Player player = null;
	



@EventHandler
public void onClose(InventoryCloseEvent event){	
	
	if (event.getInventory().getHolder() instanceof Player){
		player = (Player) event.getPlayer();
		checkStatsAndSet(player);
		
	}
}




public static void onDamageCheckWeaponStats(Player player){		

	resetWeaponStats();
	ItemStack weapon = player.getInventory().getItemInMainHand();
	
	if (weapon == null)return;
	if (weapon.getItemMeta() == null)return;		
	if (weapon.getItemMeta().getLore() == null)return;	
	
	if (functions.ifWeapon(weapon)){		
		
		List<String> lores = weapon.getItemMeta().getLore();	
	
		for (int i = 0 ; i< weaponStatNames.length ; i++){
			
			if (functions.loreEquals(lores, weaponStatNames[i]) > -1 )
			{weaponStatNumbers.set(i, weaponStatNumbers.get(i) +functions.getNumber(lores.get(functions.loreEquals(lores, weaponStatNames[i]))));}
		
			}

	
		setWeaponStats();		
		
		player.sendMessage("" + DMG);		
		
		
	}
	
	
}




public static void checkStatsAndSet(Player player){
	
	
	
List<ItemStack> Gear = new ArrayList<ItemStack>();	


	// adds all gear to Gear list
	for (ItemStack item : player.getInventory().getArmorContents()){if (item != null); Gear.add(item);}	

	resetStats();
	
	
	for (ItemStack item: Gear){
		
		if (item == null)continue;
		if (item.getItemMeta() == null)continue;		
		if (item.getItemMeta().getLore() == null)continue;
		
		ItemMeta meta = item.getItemMeta();
		
		List<String> lores = meta.getLore();
		
		for (int i = 0 ; i< armorStatNames.length ; i++){
		
		if (functions.loreEquals(lores, armorStatNames[i]) > -1 ){armorStatNumbers.set(i, armorStatNumbers.get(i) +functions.getNumber(lores.get(functions.loreEquals(lores, armorStatNames[i]))));}
	
		}
		
				
	}
	
	setArmorStats();	

	int totalHP = HP + (HP * HPPERC / 100);
	
	if (totalHP == 0){totalHP = 20;}
	player.setMaxHealth(totalHP);
	player.setHealthScale(20);
	
	
	
	

	resetWeaponStats();
	ItemStack weapon = player.getItemInHand();
	
	if (weapon == null)return;
	if (weapon.getItemMeta() == null)return;		
	if (weapon.getItemMeta().getLore() == null)return;	
	
	if (functions.ifWeapon(weapon)){	
		
		List<String> lores = weapon.getItemMeta().getLore();	
	
		for (int i = 0 ; i< weaponStatNames.length ; i++){
			
			if (functions.loreEquals(lores, weaponStatNames[i]) > -1 )
			{weaponStatNumbers.set(i, weaponStatNumbers.get(i) +functions.getNumber(lores.get(functions.loreEquals(lores, weaponStatNames[i]))));}
		
			}

	
		setWeaponStats();		
		
		
		
	}
	
	
	
}	  










}
