package Menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import combat.statcheck;
import combat.stats;
import database.items;
import net.md_5.bungee.api.ChatColor;
import util.functions;


public class CHARACTER implements Listener {	
	

Inventory charmenu = null;

@EventHandler
	public void clickCompass(PlayerInteractEvent event){	
	
	Player player = event.getPlayer();		
	
	if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
	if (player.getInventory().getItemInMainHand().getType() != Material.COMPASS){return;}	
	
	statcheck.checkStatsAndSet(player);
	
	createMenu(player);  
	}
	}



public String col(String s){return ChatColor.RED + s;}
	

	public void createMenu(Player player){
		
		ItemStack armorStats = new ItemStack(Material.LEATHER_CHESTPLATE);
		
    	String[] armorStatsLore =		
    	{
		col("HP: ") + ChatColor.GREEN +  stats.HP + "%",    	
		col("HP REGEN: ") +ChatColor.GREEN +  stats.HPREGEN,
		col("HP PERC: ") +ChatColor.GREEN +  stats.HPPERC + "%",
		col("ARMOR: ") + ChatColor.GREEN + stats.ARMOR + "%",
		col("BLOCK: ") + ChatColor.GREEN + stats.BLOCK + "%",
		col("DODGE: ") + ChatColor.GREEN + stats.DODGE + "%",
		col("REFLECT: ") + ChatColor.GREEN + stats.REFLECT+ "%",
		col("HEAL CHANCE: ") + ChatColor.GREEN + stats.HEALCHANCE+ "%",
		col("FIRE DMG: ") + ChatColor.GREEN + stats.FIREDMGBONUS+ "%",
		col("ICE DMG: ") + ChatColor.GREEN + stats.ICEDMGBONUS+ "%",
		col("POISON DMG: ") + ChatColor.GREEN + stats.POISONDMGBONUS+ "%",
		col("PURE DMG: ") + ChatColor.GREEN + stats.PUREDMGBONUS+ "%",		
    			
    	};
    	
    	/*String[] weaponStatsLore =
    	{
    	col("DMG: ") + ChatColor.GREEN + stats.DMG,	
    	col("FIRE DMG: ") + ChatColor.GREEN + stats.FIREDMG,
    	col("ICE DMG: ") + ChatColor.GREEN + stats.ICEDMG,
    	col("PURE DMG: ") + ChatColor.GREEN + stats.PUREDMG,
    	col("POISON DMG: ") + ChatColor.GREEN + stats.POISONDMG,
    	col("CRIT DMG: ") + ChatColor.GREEN + stats.CRITDMG,
    	col("CRIT CHANCE: ") + ChatColor.GREEN + stats.CRITCHANCE,
    			
    	};
    	
    	*/
    	
    	
    	armorStats = items.makeItem(armorStats, ChatColor.BLUE + "Armor stats", armorStatsLore);
		
	
		charmenu = Bukkit.createInventory(null, 9, ChatColor.GREEN + "" +ChatColor.BOLD +  "Character Menu");
		
		charmenu.setItem(8, armorStats);
		

        player.openInventory(charmenu); 
        
   
	}
	
	
	
	
@EventHandler
public void stopItemsBeingClicked(InventoryClickEvent event){
	
	if (event.getSlot() < 0){return;}    
	if (event.getClickedInventory().getName() == null){return;}
	

	String invname = functions.getText(event.getClickedInventory().getName());
	
	if (!invname.equals("CharacterMenu"))return;	
	
		event.setCancelled(true);		
	}
	
	
	

	
	
	
}
