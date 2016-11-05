package classes;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.Player;
import util.functions;


public class IDENTIFICATION implements Listener{       
  
    
    private final ItemFlag HIDE_ATTRIBUTES = null;

	// ITEM INDENTIFYING
   
	@EventHandler
	public boolean onInventoryClick(InventoryClickEvent event) {
    	
    	int statNumber = 0;
    	String rarity = "";
    	int tier = 0;
    	
        Player player = (Player) event.getWhoClicked();
        	if(event.isLeftClick()){       
        		
        		// ############################################
        		// 			LONG NULL CHECKS MOVE ON 
        		// ############################################
        		
        		
            	if(event.getSlot() < 0){return false;}            	
            	int slot = event.getSlot(); 
            	
            	if (event.getWhoClicked().getInventory().getItem(slot) == null){return false;}  
            	
            	ItemStack itemstack = event.getWhoClicked().getInventory().getItem(slot);	
            	
				if(itemstack.getItemMeta() == null ){return false;}		
				
				ItemMeta meta = itemstack.getItemMeta();
                List<String> lores = new ArrayList<String>();
                
                if (meta.getLore() == null){return false;} 
                
                lores = meta.getLore();             
                
                if (lores.contains("§a§lStats:")){return false;}                
                if (!lores.contains("Click to Identify")){return false;}
                
                              
                // removes unindentified
                if (!lores.contains("§a§lStats:") && lores.contains("Click to Identify") ){
                	
                	lores.set(functions.loreEquals(lores, "ClicktoIdentify"), "");              	
                
                }     
                else {return false;}
                	
                
        		// ############################################
        		// 			LONG NULL CHECKS END HERE 
        		// ############################################
                
               boolean ifGear = false; 	
                              	
               
	               
	       		// ############################################
	       		// 		 			IF ARMOR 
	       		// ############################################
               
               if (functions.ifArmor(itemstack)){ 
                	            
                	ifGear = true;
                                  	
                	int minArmor[] = {10,50,250,1000,5000};
                	                  
                	                	
                	tier = functions.tierNumber(itemstack);
                	
                  	
                	String[] results = functions.randomRarity(minArmor,tier);                	
                	rarity = results[0];
                    statNumber = Integer.parseInt(results[1]);  
                	
                   // we finalize the main stat here                 	
                	lores.set(0,ChatColor.RED + "HP: +" + statNumber ); 
                	
             	
         		// ############################################
         		// 		   	ADD LORES INSIDE FUNCTIONS	 
         		// ############################################  
                	
                	functions.armorRandomStats(lores);
               
                 }
                                

        		// ############################################
        		// 					IF WEAPON 
        		// ############################################
                
               if (functions.ifWeapon(itemstack)){                                          
                	
                	ifGear = true;
                	                	
                	int minWeapon[] = {5,25,125,625,3150};                	             	        	
                	
                	tier = functions.tierNumber(itemstack);
                	
                	
                	String[] results = functions.randomRarity(minWeapon , tier);                	
                	rarity = results[0];
                    statNumber = Integer.parseInt(results[1]);                	
                	                	
                   // we finalize the main stat here                 	
                	lores.set(0,ChatColor.RED + "DMG: +" + statNumber );               	                 		
                	
                 	
             		// ############################################
             		// 		   	ADD LORES INSIDE FUNCTIONS	 
             		// ############################################  
                	
                	functions.weaponRandomStats(lores);                	
                    
                    }            
                
                
                if (ifGear){
                	
                	// we finally add rarity                            
                    lores.add(ChatColor.ITALIC + rarity);
                    lores.add("§8Durability: 1500/1500");
                    lores.add(ChatColor.GRAY + "Re-rolls left: 10" );
                    
                }
                   
                // removes the pesky vanilla minecraft dmg lore
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);         
                             
                meta.setLore(lores);
                itemstack.setItemMeta(meta);
                player.sendMessage(functions.succeedMessage("Item Identified!"));
                return true;
                
                }
                                      
                 		
            
        return false;
}
    

      
    

} // class



