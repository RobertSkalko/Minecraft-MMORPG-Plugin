package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.functions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.Player;
import org.bukkit.Sound;


public class ENCHANTING implements Listener{
	

	static String tierColor = null;	
	
	static Player player = null;
   
	@EventHandler
	public void onEnchantClick(InventoryClickEvent event) {
    	      	  
    	
		// ############################################
		// 			LONG NULL CHECKS MOVE ON 
		// ############################################
    	
    	    player = (Player) event.getWhoClicked();
    	    
    	    if(event.getCurrentItem() == null)return;        	
        	if(player.getItemOnCursor() == null)return;     		    		    	
        	if(!event.getCurrentItem().hasItemMeta())return;        	
        	if(!player.getItemOnCursor().hasItemMeta())return;    	 
        	
        	// item > enchant  	 
            if(ifBothTiersSame(event.getCurrentItem().getType().toString(),player.getItemOnCursor().getItemMeta().getDisplayName().toString())){
                  
          							
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
	        		
                
                if (lores.get(0).contains("HP") || lores.get(0).contains("DMG")  ){                  	
                               	
                
                String enchantLore = lores.get(0).toString().replaceAll("[^a-zA-Z]", "").replaceAll("c","");
                
                int powderBonus = 0;
                int powderLore = functions.loreEquals(lores, "Powder");
                if (powderLore > -1){
                powderBonus = functions.getNumber(lores.get(powderLore));
                }
                
                if (powderBonus < 0){powderBonus = 0;}
                
                int enchantNumber = functions.getNumber(lores.get(0));             
                
      
                // reduces item stack and if 1, deletes item
                int itemAmount = player.getItemOnCursor().getAmount();
                if (itemAmount < 2){player.setItemOnCursor(null);}
                else{player.getItemOnCursor().setAmount(--itemAmount);}                      	
              	
                event.setCancelled(true); // this means the item swap is canceled, so you can repeatadely click items              		
                
                String itemName = itemstack.getItemMeta().getDisplayName().toString();
                itemName = ChatColor.stripColor(itemName);
               
                String itemNameWithoutLevel = itemName.replaceAll("[^a-zA-Z\\u0020]", "");
                int upgradeLevel = functions.getNumber(itemName);
                
                int finalLevel = 0;
                        	
            	// if there's a level at all, otherwise it's 1
            	if (upgradeLevel != -1){
            	finalLevel = upgradeLevel;
            	}
            	                
                Random ran = new Random();                
                int enchantRoll = ran.nextInt(100 + powderBonus);
                
                // if it enchants succesfully
                if (enchantRoll  > finalLevel + 10){
                	double finalEnchant = enchantNumber * 1.05;                	
                	finalLevel++;
                	int intEnchant = (int)finalEnchant;     
                	
                              	         
                                                                
                	functions.succeedAnimation(player);
                	
                      
                 
                	
                	// we add [number] + space + name
                	meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD +  "+[" + finalLevel + "] " + tierColor + "" + ChatColor.BOLD + itemNameWithoutLevel.trim() );                	
                	if (intEnchant < 50){intEnchant++;}
                	lores.set(0, ChatColor.RED + enchantLore + ": +" + intEnchant);
                	if (powderLore > -1){
                	lores.remove(powderLore);
                	}
                	
                    meta.setLore(lores);
                    itemstack.setItemMeta(meta);
                	             	
                }
                // else it fails to enchant
                else {
                	// see if it will break
                	int breakRoll = ran.nextInt(100);
                	if (breakRoll < finalLevel * 2){
                	event.setCurrentItem(null);
                	
                	player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND , 10 , 0);
                	
                	return;
                	}
                	                  	
                }
              
            
                
                
                } // if lore contains hp
            }
            
    }

	
	
  	
	   
    public static boolean ifBothTiersSame(String item, String enchant){
    	
    	
    		if (item != null && enchant != null && enchant.contains("Enchant Tier 1 equipment")){
    		if (item.contains("LEATHER") ||	item.contains("WOOD")){tierColor = "§7";return true;}}
    		
    		
    		if (item != null && enchant != null && enchant.contains("Enchant Tier 2 equipment")){
    		if (item.contains("CHAINMAIL") || item.contains("STONE")){tierColor = "§7";return true;}}
    	
    	        	  	
    		if (item != null &&
        		enchant != null &&
        		enchant.contains("Enchant Tier 3 equipment") &&
        		item.contains("IRON") 
        		){
    			tierColor = "§b";
        		return true;    		
        	}
        	  	
    		if (item != null &&
        		enchant != null &&
        		enchant.contains("Enchant Tier 4 equipment") &&
        		item.contains("DIAMOND") 
        		){
    			tierColor = "§5";
        		return true;    		
        	}
        	  	
    		if (item != null &&
        		enchant != null &&
        		enchant.contains("Enchant Tier 5 equipment") &&
        		item.contains("GOLD") 
        		){
    			tierColor = "§e";
        		return true;    		
        	}

    
    	
    	
    return false;

}
    
    
    
    
    
    
    
	
} // end class
