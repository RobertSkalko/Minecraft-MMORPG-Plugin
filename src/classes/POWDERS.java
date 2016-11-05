package classes;


import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import util.functions;


public class POWDERS implements Listener{	


	@EventHandler
	public void onPowder(InventoryClickEvent event){ 
    	      	  
    	
		// ############################################
		// 			LONG NULL CHECKS MOVE ON 
		// ############################################
    	
    	    Player player = (Player) event.getWhoClicked();
    	    
    	    if(event.getCurrentItem() == null)return;        	
        	if(player.getItemOnCursor() == null)return;     		    		    	
        	if(!event.getCurrentItem().hasItemMeta())return;        	
        	if(!player.getItemOnCursor().hasItemMeta())return;    
        	if(event.getCurrentItem().getItemMeta().getDisplayName() == null)return; 
        	
        	ItemStack item = event.getCurrentItem();
        	ItemStack heldItem = player.getItemOnCursor();
        	
        	// item > enchant  	 
            if(functions.ifGear(item.getType().toString()) &&
               heldItem.getItemMeta().getDisplayName().toString().contains("Powder")
            		){
                  
          							
				if (event.getSlot() < 0){return;}; 
				
				int slot = event.getSlot();					
				
				if (event.getWhoClicked().getInventory().getItem(slot) == null){return;}
				
			    List<String> heldLores =  heldItem.getItemMeta().getLore();
				List<String> lores = item.getItemMeta().getLore();
	
				                
			
		// ############################################
		// 			NULL CHECKS END HERE
		// ############################################
	        		
            if (functions.loreEquals(lores, "Powder") < 0){
				
				event.setCancelled(true);
				
				String powderLore = functions.giveLoreContains(heldLores , "Powder");
			
				lores.add(powderLore);		
				
				
				  int itemAmount = player.getItemOnCursor().getAmount();
	              if (itemAmount < 2){player.setItemOnCursor(null);}
	              else{player.getItemOnCursor().setAmount(--itemAmount);}  
				
	              
	              functions.succeedAnimation(player);
	              
	              ItemMeta meta = item.getItemMeta();
	              meta.setLore(lores);	              
                  item.setItemMeta(meta);                 
				
                
            }
				
            }
            
    }

	
	
	
	
	
	
}
