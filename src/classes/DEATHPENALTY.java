package classes;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.functions;
import org.bukkit.entity.Player;




public class DEATHPENALTY implements Listener {    
 
    static Player player = null;    
    static List<String> lores = new ArrayList<String>();
 	static int duraLore = -1;
    
      	    		      	
  	static double currentRealDura = 0;
  	static double maxRealDura = 0;
  	
    // Dura loss on death
  
 	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
    	
     player = event.getEntity();
      
     player.sendMessage(functions.warningMessage("You have lost 33% gear durability due to dying"));
     ItemStack item = null;
    	     
		// ############################################
		// LOOP INVENTORY AND REDUCE DURABILITY ON GEAR
		// ############################################
   
     
     for (int y = 0; y < player.getInventory().getContents().length; y++){
    	 
     item = player.getInventory().getItem(y);
     
    	    if (item != null && functions.ifRepairable(item.getType().toString())) {  
    	    	 
    	    	 if (item.hasItemMeta() && item.getItemMeta().getLore() != null){  	  
    	    		 
    	    	   ItemMeta itemMeta = item.getItemMeta();
    	    	   lores = item.getItemMeta().getLore();    	    	  
    	    		
    	    	   duraLore = functions.loreEquals(lores, "Durability");    	    	   
    	    	    	    	   
    	    			if (duraLore > -1){
    	    				
    	    				maxRealDura = item.getType().getMaxDurability();
    	    				
    	    				doDuraLoss();			    		      	    		          
    	    		              	    		          
    	    		        item.setDurability((short) currentRealDura); 
    	    				
    	    				}    	    			
    	    		
    	    		if (lores == null){player.getInventory().setItem(y,null);}
    	    		else{itemMeta.setLore(lores);item.setItemMeta(itemMeta);
    	    		player.getInventory().setItem(y, item);}}	

    	        } 
    	        	
    	player.updateInventory();
     }     
     
}    
    
    
		// ############################################
		// 			REDUCE DURA / SET TO NULL IF 0
		// ############################################

 	public void doDuraLoss(){ 		
              
        int firstLoreDura = functions.getFirstNumber(lores.get(duraLore));
        int secondLoreDura = functions.getSecondNumber(lores.get(duraLore)); 
        firstLoreDura -= secondLoreDura / 3;  
        
        if (firstLoreDura > 0){lores.set(duraLore,ChatColor.GRAY + "Durability: " + firstLoreDura + "/" + secondLoreDura);}
        else if (firstLoreDura <= 0){lores = null;}	               
     	   	    		      	
      	double ratio = maxRealDura / secondLoreDura;	    	    		      	
      	currentRealDura = (secondLoreDura - firstLoreDura) * ratio;       
 		
 	}
      	
 	
} // class



