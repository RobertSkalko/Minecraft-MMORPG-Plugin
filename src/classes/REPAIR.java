package classes;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.Player;
import util.functions;

public class REPAIR implements Listener {
	
	String tierColor = null;
	
   
	@EventHandler
	public void onRepairClick(InventoryClickEvent event) {
    	      	  
    	
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
    
            
            event.setCancelled(true); // this means the item swap is canceled, so you can repeatadely click items 
			
           int duraLore = 0; 
           for (int x=0;x<lores.size();x++){
        	   if (lores.get(x).contains("Durability") ){
        		   duraLore = x;
        		   break;        		   
        	   }
        	   if (x == lores.size() -1 && duraLore == 0){
        		   return;        		   
        	   }
           }
                                       
			
           int firstDura = functions.getFirstNumber(lores.get(duraLore));
           int secondDura = functions.getSecondNumber(lores.get(duraLore));
           
           // no need for repairs
           if (firstDura == secondDura){return;}
           
           // this is what repair does
           firstDura = firstDura + 100;
           if (firstDura > secondDura){firstDura = secondDura;}
           
           String finalDurability = ChatColor.GRAY + "Durability: " + firstDura + "/" + secondDura; 
            
           lores.set(duraLore, finalDurability);
                
           meta.setLore(lores);
           itemstack.setItemMeta(meta);
           
           // reduces item stack and if 1, deletes item
           int itemAmount = player.getItemOnCursor().getAmount();
           if (itemAmount < 2){player.setItemOnCursor(null);}
           else{player.getItemOnCursor().setAmount(--itemAmount);}
           
          

      	double maxDura = itemstack.getType().getMaxDurability();
      	
      	double ratio = maxDura / secondDura;	
      	
      	double setAmount = (secondDura - firstDura) * ratio;
          
          
          
          functions.repairAnimation(player);
          
          itemstack.setDurability((short) setAmount); 
          
             }
            
            
    }

	public boolean ifBothTiersSame(String item, String enchant){
		
   	
	if (item != null && enchant != null && enchant.contains("Repairs Tier 1")){
	if (item.contains("LEATHER") ||	item.contains("WOOD")){	return true;}}
	
	
	if (item != null && enchant != null && enchant.contains("Repairs Tier 2")){
	if (item.contains("CHAINMAIL") || item.contains("STONE")){return true;}}
	
	  	
	if (item != null &&	enchant != null && enchant.contains("Repairs Tier 3") && item.contains("IRON")){    		
		return true;    		
	}
	  	
	if (item != null &&	enchant != null && enchant.contains("Repairs Tier 4") && item.contains("DIAMOND")){    		
		return true;    		
	}
	  	
	if (item != null && enchant != null && enchant.contains("Repairs Tier 5") && item.contains("GOLD")){    		
		return true;    		
	}
	

    	
    	
    return false;

}
    
    
    
    
	
} // end class
