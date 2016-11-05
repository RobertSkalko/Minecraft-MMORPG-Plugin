package classes;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.functions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.Player;

public class REROLL implements Listener{
	
	String tierColor = null;   
	

	@EventHandler
	public void onAlterationClick(InventoryClickEvent event) {
    	      	  
    	
		// ############################################
		// 			LONG NULL CHECKS MOVE ON 
		// ############################################
    	
    	    Player player = (Player) event.getWhoClicked();
    		if (event.getCurrentItem() == null){return;}
    		if (player.getItemOnCursor() == null){return;}  
    		if (event.getCurrentItem().getType() == null){return;}
    		if (player.getItemOnCursor().getItemMeta() == null){return;}  
    		if (event.getCurrentItem().getType().toString() == null){return;}
    		if (player.getItemOnCursor().getItemMeta().getDisplayName() == null){return;}    	
        	if(!event.getCurrentItem().hasItemMeta())return;        	
        	if(!player.getItemOnCursor().hasItemMeta())return;    	 
        	
        	// item > repair  	 
            if(ifBothTiersSame(event.getCurrentItem().getType().toString(),ChatColor.stripColor(player.getItemOnCursor().getItemMeta().getDisplayName().toString()))){
                  
          							
				if (event.getSlot() < 0){return;}; 
				
				int slot = event.getSlot();					
				
				if (event.getWhoClicked().getInventory().getItem(slot) == null){return;}
				
				ItemStack itemstack = event.getWhoClicked().getInventory().getItem(slot);	
				
				if(itemstack.getItemMeta() == null ) {return;}
				
			    ItemMeta meta = itemstack.getItemMeta();						
				                
			    List<String> lores = new ArrayList<String>();
				
				if (meta.getLore() == null){return;}
				
				lores = meta.getLore();
				
				if (itemstack.getItemMeta().getDisplayName() == null){return;}
				                
			
		// ############################################
		// 			NULL CHECKS END HERE
		// ############################################
    
            
            event.setCancelled(true); // this means the item swap is canceled, so you can repeatadely click items 
			
            String firstLore = lores.get(0);         
            String rerollLore = null;  
            String powderLore = null;
            String rarityLore = lores.get(functions.loreContains(lores, "Rarity"));
            String duraLore = lores.get(functions.loreContains(lores, "Durability"));            
            if (functions.loreContains(lores, "Rerolls") > -1 ){rerollLore = lores.get(functions.loreContains(lores, "Rerolls"));}        
            if (functions.loreContains(lores, "Powder") > -1 ){powderLore = lores.get(functions.loreContains(lores, "Powder"));}
            
            if (rerollLore == null)return;
            
            int rerollsLeft = functions.getNumber(rerollLore);
            
            if (rerollsLeft < 1){player.sendMessage(functions.warningMessage("You cannot Re-roll this item anymore!"));return;}            
            rerollsLeft--;
            rerollLore = ChatColor.YELLOW + "Re-rolls left: " + rerollsLeft;
            
            lores = new ArrayList<String>();
            lores.add(firstLore);            
            
               
    		// ############################################
    		// 			WEAPON / ARMOR
    		// ############################################
            
            
			 if (functions.ifArmor(itemstack)){         	         	
			 	lores = functions.armorRandomStats(lores);  
			  }
                         
         
         
         	if (functions.ifWeapon(itemstack)){   
        	 lores = functions.weaponRandomStats(lores);
             }         
                        
          
           lores.add(rarityLore);
           lores.add(duraLore);      
           if (rerollLore != null){lores.add(rerollLore);} 
           if (powderLore != null){lores.add(powderLore);}
                     
           meta.setLore(lores);
           itemstack.setItemMeta(meta);
           
           // reduces item stack and if 1, deletes item
           int itemAmount = player.getItemOnCursor().getAmount();
           if (itemAmount < 2){player.setItemOnCursor(null);}
           else{player.getItemOnCursor().setAmount(--itemAmount);}
          
           
           functions.succeedAnimation(player);
           
           player.sendMessage(functions.succeedMessage("Item Re-rolled!"));
          
             }
            
            
    }

	public static boolean ifBothTiersSame(String item, String enchant){
    	
	if (item != null && enchant != null && enchant.contains("Tier 1: Orb of Alteration")){
	if (item.contains("LEATHER") ||	item.contains("WOOD")){	return true;}}
	
	
	if (item != null && enchant != null && enchant.contains("Tier 2: Orb of Alteration")){
	if (item.contains("CHAINMAIL") || item.contains("STONE")){return true;}}
	
	  	
	if (item != null &&	enchant != null && enchant.contains("Tier 3: Orb of Alteration") && item.contains("IRON")){    		
		return true;    		
	}
	  	
	if (item != null &&	enchant != null && enchant.contains("Tier 4: Orb of Alteration") && item.contains("DIAMOND")){    		
		return true;    		
	}
	  	
	if (item != null && enchant != null && enchant.contains("Tier 5: Orb of Alteration") && item.contains("GOLD")){    		
		return true;    		
	}
	

    	
    	
    return false;

}
    
    
    
    
	
} // end class
