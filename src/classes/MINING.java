package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import util.functions;

public class MINING implements Listener{
	
	 String tierColor = null;	
 
	 Player player = null;
	
	 int doubleChance = 0;
	 int tripleChance = 0;
	 int gemChance = 0;
	 int repairChance = 0;
	 int doubleRepairChance = 0;
	 int currentExp = 0;
	 int currentLevel = 0;
	
	 int xpMult = 500;
	 int xpGain = 0;
	
	 int levelLore = -1;
	 int expLore = -1;
	 int duraLore = -1; 
	 int repairLore = -1;
	 int doubleRepairLore = -1;
	 int doubleLore = -1;
	 int tripleLore = -1;
	 int gemLore = -1;
	
     ItemStack pickaxe = null;
	 int firstDura = 1;
	 int secondDura = 1;
	 ItemMeta meta = null;
	
	 List<String> lores = null;
   
	
	

	@EventHandler
	public void onMineBlock(BlockBreakEvent event) { 
		
	//	 event.setCancelled(true); // this means the block isn't mined, but stays as ore so it can get transformed into stone
    	      	  
    	
		// ############################################
		// 			LONG NULL CHECKS MOVE ON 
		// ############################################
    	
		 	if (event.getPlayer() == null){return;}		 
    	    player = (Player) event.getPlayer();   	 
    	    if (player.getItemInHand().getItemMeta().getLore() == null){return;}    	   
    	    if (event.getBlock().getType() == null){return;}
    	   
    	   
    	    pickaxe = player.getItemInHand();
    	    Material block = event.getBlock().getType();
    	    String pickaxeMat = pickaxe.getType().toString();
    	    meta = pickaxe.getItemMeta();
    		if (meta.getLore() == null){return;} 
    		
		// ############################################
		// 			NULL CHECKS END HERE - mostly
		// ############################################

        	// item > mine 	 
            if (pickaxeGoodEnough(pickaxeMat , block.toString())){           
          					
            	doubleChance = 0;
            	tripleChance = 0;
            	gemChance = 0;
            	repairChance = 0;
            	doubleRepairChance = 0;
            	
            	ItemMeta meta = pickaxe.getItemMeta();                
  			    lores = new ArrayList<String>();			 				
  				lores = meta.getLore();		
            	
        		// ############################################
        		// 		   CHECK PICKAXE STATS NOW
        		// ############################################
            	
            	checkPickaxeStats(pickaxe.getItemMeta().getLore().toString());
            	
        		// ############################################
        		// 		   GIVE MINING REWARDS TO PLAYER
        		// ############################################
            	
            	givePlayerRewards(block.toString());
            	
            	
        		// ############################################
        		// 			SET MINED BLOCK TO STONE
        		// ############################################
                  	
            	event.getBlock().setType(Material.STONE);
				
        		// ############################################
        		// 	  GIVE EXP TO PLAYER AND CHECK LEVELS
        		// ############################################                  	
            	
            	doExp();         	   	  
				
        		// ############################################
        		// 	            SET PICKAXE LORES
        		// ############################################           
            	
                meta.setLore(lores);
                pickaxe.setItemMeta(meta);  
            	
            }
            
    	  }
    	
	public void checkPickaxeStats(String pickaxe){
		
	        duraLore = -1; 
	        repairLore = -1;
	        doubleRepairLore = -1;
	        doubleLore = -1;
	        tripleLore = -1;
	        gemLore = -1;  
	        levelLore = -1;
	        expLore = -1;
	        

		for (int x = 0; x < lores.size(); x++) {
			
			String lore = functions.getText(lores.get(x));
			
			if (lore.equals("Durability")) {
				duraLore = x;
				firstDura = functions.getFirstNumber(lores.get(duraLore));
				secondDura = functions.getSecondNumber(lores.get(duraLore));
			}
			if (lore.equals("GEMCHANCE")) {
				gemLore = x;
				gemChance = functions.getNumber(lores.get(gemLore));
			}
			if (lore.equals("DOUBLEORE")) {
				doubleLore = x;
				doubleChance = functions.getNumber(lores.get(doubleLore));
			}
			if (lore.equals("TRIPLEORE")) {
				tripleLore = x;
				tripleChance = functions.getNumber(lores.get(tripleLore));
			}
			if (lore.equals("REPAIRCHANCE")) {
				repairLore = x;
				repairChance = functions.getNumber(lores.get(repairLore));
			}
			if (lore.equals("DOUBLEREPAIRCHANCE")) {
				doubleRepairLore = x;
				doubleRepairChance = functions.getNumber(lores.get(doubleRepairLore));
			}
			if (lore.equals("LEVEL")) {
				levelLore = x;
				currentLevel = functions.getNumber(lores.get(levelLore));
			}
			if (lore.equals("EXP")) {
				expLore = x;
				currentExp = functions.getFirstNumber(lores.get(expLore));
			}
		}
           
                  
           
	}
	



	public void givePlayerRewards(String block){
		
		ItemStack rewardOre = null;
		int numberOfOres = 1;
		firstDura--;
		
		Random ran = new Random(); 
		int chance = 0;
               
        chance = ran.nextInt(100);        
        if (chance < doubleChance){numberOfOres = numberOfOres + 1;}
        
        chance = ran.nextInt(100);
        if (chance < tripleChance){numberOfOres = numberOfOres + 2;}
        
        chance = ran.nextInt(100);    
        if (chance < repairChance){firstDura++;}
        
        chance = ran.nextInt(100);
        if (chance < doubleRepairChance){firstDura = firstDura + 2;}
        
        ChatColor nameColor = null;
        String name = null;
        
		if (block == "COAL_ORE"){rewardOre = new ItemStack (Material.COAL_ORE , numberOfOres);xpGain = 100;nameColor = ChatColor.GRAY;name = "Tier 1: Coal Ore";}
		if (block == "EMERALD_ORE"){rewardOre = new ItemStack (Material.EMERALD_ORE , numberOfOres);xpGain = 200;nameColor = ChatColor.DARK_GREEN;name = "Tier 2: Emerald Ore";}
		if (block == "IRON_ORE"){rewardOre = new ItemStack (Material.IRON_ORE , numberOfOres);xpGain = 250;nameColor = ChatColor.AQUA;name = "Tier 3: Iron Ore";}
		if (block == "DIAMOND_ORE"){rewardOre = new ItemStack (Material.DIAMOND_ORE , numberOfOres);xpGain = 500;nameColor = ChatColor.LIGHT_PURPLE;name = "Tier 4: Diamond Ore";}
		if (block == "GOLD_ORE"){rewardOre = new ItemStack (Material.GOLD_ORE , numberOfOres);xpGain = 1000;nameColor = ChatColor.YELLOW;name = "Tier 5: Gold Ore";}
		
		if (rewardOre == null){return;}
		
		
		List<String> rewardLores = new ArrayList<String>();	
		rewardLores.add(ChatColor.GRAY + "A piece of Ore");
		rewardLores.add(ChatColor.GRAY + "Used as currency at Merchants!");		
		ItemMeta oreLore = rewardOre.getItemMeta();			
		
		oreLore.setDisplayName(nameColor + name);
        oreLore.setLore(rewardLores);
        rewardOre.setItemMeta(oreLore);         
		
		
		// SCALE GEM REWARDS HERE
		int gemAmount = 16;
		ItemStack rewardGems = null;
	    chance = ran.nextInt(100);
        if (chance < gemChance){
    		if (block == "COAL_ORE"){rewardGems = new ItemStack (Material.EMERALD , gemAmount);}
    		if (block == "EMERALD_ORE"){rewardGems = new ItemStack (Material.EMERALD , gemAmount * 2);}
    		if (block == "IRON_ORE"){rewardGems = new ItemStack (Material.EMERALD , gemAmount * 3);}
    		if (block == "DIAMOND_ORE"){rewardGems = new ItemStack (Material.EMERALD , gemAmount * 4);}
    		if (block == "GOLD_ORE"){rewardGems = new ItemStack (Material.EMERALD , gemAmount * 5);}
        }
		  
                
		player.getInventory().addItem(rewardOre);
		
		if (rewardGems == null){return;}
		
		player.getInventory().addItem(rewardGems);
		
	}
	
	public void doExp(){
		
		currentExp += xpGain;
		int xpToLevel = currentLevel * xpMult;
		
		if (currentExp >= xpToLevel){
		currentLevel++;
		currentExp = 0;			
		
		player.sendMessage(functions.succeedMessage("Pickaxe reached LVL: " + currentLevel));
		
		if (currentLevel == 20){pickaxe.setType(Material.STONE_PICKAXE);enchantPickaxe();}
		if (currentLevel == 40){pickaxe.setType(Material.IRON_PICKAXE);enchantPickaxe();}
		if (currentLevel == 60){pickaxe.setType(Material.DIAMOND_PICKAXE);enchantPickaxe();}
		if (currentLevel == 80){pickaxe.setType(Material.GOLD_PICKAXE);enchantPickaxe();}
		if (currentLevel == 100 || currentLevel > 100){pickaxe.setType(Material.WOOD_PICKAXE);enchantPickaxe();currentLevel=1;}
		
		}		
		
		xpToLevel = currentLevel * xpMult;
		
		lores.set(expLore, ChatColor.YELLOW + "EXP: " + currentExp + "/" + xpToLevel);
		lores.set(levelLore,ChatColor.YELLOW + "LEVEL: " + currentLevel);
		lores.set(duraLore, ChatColor.GRAY + "Durability: " + firstDura + "/" + secondDura);
				
	}

    public void enchantPickaxe(){
    	
    	Random ran = new Random();      
        int statAmount = ran.nextInt(3)+1;
        int whichStat = ran.nextInt(6);        
                
        if (whichStat == 1){        	
        	if (doubleChance == 0){
        	doubleChance += statAmount;
        	lores.add(ChatColor.RED + "DOUBLE ORE: " + doubleChance +"%");
        	}
        	else{
    		doubleChance += statAmount;
    		lores.set(doubleLore,ChatColor.RED +"DOUBLE ORE: " + doubleChance +"%");
        	}        	
        }
        
        if (whichStat == 2){        	
        	if (tripleChance == 0){
        	tripleChance += statAmount;
        	lores.add(ChatColor.RED +"TRIPLE ORE: " + tripleChance +"%");        	
        	}
        	else{
    		tripleChance += statAmount;
    		lores.set(tripleLore,ChatColor.RED +"TRIPLE ORE: " + tripleChance +"%");
        	}        	
        }
        
        if (whichStat == 3){        	
        	if (gemChance == 0){
        	gemChance += statAmount;
        	lores.add(ChatColor.RED +"GEM CHANCE: " + gemChance +"%"); 
        	}
        	else{
    		gemChance += statAmount;
    		lores.set(gemLore,ChatColor.RED +"GEM CHANCE: " + gemChance +"%");
        	}        	
        }
        
        if (whichStat == 4){        	
        	if (repairChance == 0){
        	repairChance += statAmount;
        	lores.add(ChatColor.RED +"REPAIR CHANCE: " + repairChance +"%");        	
        	}
        	else{
    		repairChance += statAmount;
    		lores.set(repairLore,ChatColor.RED + "REPAIR CHANCE: " + repairChance +"%");
        	}        	
        }
        
        if (whichStat == 5){        	
        	if (doubleRepairChance == 0){
        	doubleRepairChance += statAmount;
        	lores.add(ChatColor.RED +"DOUBLE REPAIR CHANCE: " + doubleRepairChance +"%");        	
        	}
        	else{
    		doubleRepairChance += statAmount;
    		lores.set(doubleRepairLore,ChatColor.RED +"DOUBLE REPAIR CHANCE: " + doubleRepairChance +"%");
        	}        	
        }
      
        
    	
    }
	
	
	public boolean pickaxeGoodEnough(String pickaxe, String block){
		
		if (pickaxe == null || block == null){return false;}
		
		if (block.contains("ORE")){
		if (pickaxe == "GOLD_PICKAXE"){return true;}
		if (pickaxe == "DIAMOND_PICKAXE" && block != "GOLD_ORE"){return true;}
		if (pickaxe == "IRON_PICKAXE" && block != "GOLD_ORE" && block != "DIAMOND_ORE"){return true;}
		if (pickaxe == "STONE_PICKAXE"){if (block == "EMERALD_ORE" || block == "COAL_ORE"){	return true;}}
		if (pickaxe == "WOOD_PICKAXE" && block == "COAL_ORE"){return true;}		
		}		
		return false;		
	}
    
    
    
	
} // end class
