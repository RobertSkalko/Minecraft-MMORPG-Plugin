package Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import util.functions;
import database.items;


public class VENDORS implements Listener{	
	
static ArrayList<ItemStack> rewards = new ArrayList<ItemStack>();
static Player player = null;
static Inventory vendor = null;
static ItemStack[] soldItems = null;

@EventHandler
public void onVillagerClick(PlayerInteractEntityEvent event){	
		
		player = event.getPlayer();		
	
		if (event.getRightClicked().getType().toString().contains("VILLAGER") ){
			
		event.setCancelled(true);		
		
		createVendor();
  	    			       
		}
	
	
	
}
	
	public void createVendor(){
		
		vendor = Bukkit.createInventory(null, 27, "Vendor");
		
		vendor.setItem(26, items.acceptTrade);

        player.openInventory(vendor); 
        
        soldItems = null;
		
	}
	
	
	@EventHandler
	public void onTradeConfirm(InventoryClickEvent event){
			
		if(event.getSlot() < 0){return;}
		
		int slot = event.getSlot(); 
    	
    	if (event.getClickedInventory().getItem(slot) == null){return;}  
		
		if (event.getClickedInventory().getName().contains("Vendor") &&  slot == 26  ){
			
		event.setCancelled(true);	
		
		soldItems = vendor.getContents(); 	
		
		 for (ItemStack i : soldItems){			    	
			 
			 if (i == null)continue;
			 if (i.getType() == null)continue;
			 
			 String type = i.getType().toString();
			 int n = i.getAmount();
			 
	    	  if (functions.ifGear(i.getType().toString())){    		 
	    		  
	    		  int tier = functions.tierNumber(i);    	
	    		  
	    		  if (tier == 0){addItems(items.T1Scrap,3);}
	    		  if (tier == 1){addItems(items.T2Scrap,3);}  
	    		  if (tier == 2){addItems(items.T3Scrap,3);}  
	    		  if (tier == 3){addItems(items.T4Scrap,3);}  
	    		  if (tier == 4){addItems(items.T5Scrap,3);}   	    		  
	    		  
	    	  }   	  
	    	  
	    	 	
	    	  if (type.contains("COAL_ORE")){addItems(items.T1Scrap,2 * n);continue;}
	    	  if (type.contains("EMERALD_ORE")){addItems(items.T2Scrap,2 * n);continue;}
	    	  if (type.contains("IRON_ORE")){addItems(items.T3Scrap,2 * n);continue;}
	    	  if (type.contains("DIAMOND_ORE")){addItems(items.T4Scrap,2 * n);continue;}
	    	  if (type.contains("GOLD_ORE")){addItems(items.T5Scrap,2 * n);continue;}
	    	
	    	  
		 }
		 
		createVendor(); 
				 
		for (ItemStack i : rewards){player.getInventory().addItem(i);}
		
		 rewards = new ArrayList<ItemStack>();
		
		} // slot
		
	} 
	


	
	@EventHandler
	public void onclose(InventoryCloseEvent event){		
		
		if (event.getInventory().getName().contains("Vendor")){
			
			soldItems = vendor.getContents(); 	
			
			for (ItemStack i : soldItems){
					if (i != null 
					&& i.getItemMeta().hasDisplayName() == true
					&& !i.getItemMeta().getDisplayName().contains("TRADE")){
				player.getInventory().addItem(i);}}
			
		}
		
		
	}
	
	
	public  void addItems(ItemStack item, int amount){
		
		for (int x = 0; x< amount; x++){
			
			rewards.add(item);
			
		}
		
	}
	
	
}
