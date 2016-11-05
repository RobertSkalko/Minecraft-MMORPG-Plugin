package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class functions {
	
	  public static boolean ifGear(String item){    	
		    
	   		if (item != null &&
	   		item.contains("SWORD") ||
            item.contains("HOE") ||
            item.contains("AXE") ||
            item.contains("SPADE") ||
            item.contains("HELMET") ||
            item.contains("CHESTPLATE") ||
            item.contains("BOOTS") ||
            item.contains("LEGGINGS")){ 
	   		
	   		return true;
	   	}
	
	
	return false;
}
	  
	  // this one includes pickaxe
	  public static boolean ifRepairable(String item){    	
		    
	   	  if (item != null &&
	   	  item.contains("PICKAXE") ||
	   	  item.contains("SWORD") ||
          item.contains("HOE") ||
          item.contains("AXE") ||
          item.contains("SPADE") ||
          item.contains("HELMET") ||
          item.contains("CHESTPLATE") ||
          item.contains("BOOTS") ||
          item.contains("LEGGINGS")){ 
	   		
	   		return true;
	   	}
	
	
	return false;
}
	  	  
	  
	  public static String[] weaponStatNamesNo = {"FIRE DMG","ICE DMG","PURE DMG","POISON DMG"};		
	  public static String[] weaponStatNames = {"LIFESTEAL","CRIT CHANCE","CRIT DMG","PvE DMG"};
	  public static int[] weaponStatMaxValue = {20,20,100,15};
	  
	  public static List<String> weaponRandomStats(List<String> lores){
		  
		  // set stats with %
		 for (int i = 0 ; i < weaponStatNames.length; i++){    
			 
		  int statChance = 35;
		  Random ran = new Random(); 
          int randomStat = ran.nextInt(weaponStatMaxValue[i])+1;                
          if (roll(statChance)){lores.add(ChatColor.RED + weaponStatNames[i] + ": +" + randomStat + "%");}
          
		 }
		 
		 // now set stats with no %
		 
		 int weaponDMG = getNumber(lores.get(0));
	
		 for (int i = 0 ; i < weaponStatNamesNo.length; i++){    
			 
			  int statChance = 35;
			  Random ran = new Random(); 
	          int randomStat = ran.nextInt((weaponDMG / 3) +1)+1;                
	          if (roll(statChance)){lores.add(ChatColor.RED + weaponStatNamesNo[i] + ": +" + randomStat);}
	          
			 }
		 
		 
		 
		  return lores;
	  }
	  
	  
	  
	  
	  
	  public static boolean roll(int chance){
		  
		  Random ran = new Random(); 
		  int randomInt = ran.nextInt(100);
		  
		  if (chance > randomInt){return true;}
		  return false;	  		  
	  }
	  
	  
	  
	  public static String[] armorStatNames = {"BLOCK","DODGE","REFLECT","HEAL CHANCE","ICE DMG BONUS","FIRE DMG BONUS","PURE DMG BONUS","POISON DMG BONUS","HP PERC"};
	  public static int[] armorStatMaxValue = {10,10,10,10,20,20,20,20,15};
	  
	  
	  public static List<String> armorRandomStats(List<String> lores){		  
		  	  
	         Random ran = new Random();          
	         
	         int randomStat;
	         int statChance = 25;
	         
	         randomStat = ran.nextInt(15)+1;                
	         lores.add(ChatColor.RED + "ARMOR: +" + randomStat + "%");
	         
	         
	         if (ran.nextInt(5) > 2){
	         
	         int itemHP = getNumber(lores.get(0));
	         int randomHPRegen = ran.nextInt( itemHP / 3 ) + itemHP / 10 + 1;	         
	         lores.add(ChatColor.RED + "HP REGEN: +" + randomHPRegen);    
	         
	         }
	         
	         else{
	      
	         int randomENE = ran.nextInt(3) + 1;	         
	         lores.add(ChatColor.RED + "ENE: +" + randomENE);   
	         }
	                                     
			 for (int i = 0 ; i < armorStatNames.length; i++){    
				 
				  statChance = 20;
				  ran = new Random(); 
		          randomStat = ran.nextInt(armorStatMaxValue[i])+1;                
		          if (roll(statChance)){lores.add(ChatColor.RED + armorStatNames[i] + ": +" + randomStat + "%");}
		          
				 }
		  
		  
		  return lores;
	  }
		  
	  
		public static boolean ifArmor(ItemStack itemstack){
			
		String type = itemstack.getType().toString();
			
		if (type.contains("HELMET") ||
			type.contains("LEGGINGS") ||
			type.contains("BOOTS") ||
			type.contains("CHESTPLATE") ){	  			
			return true;
			}
			return false;
			}
	  
	  	  
		public static boolean ifWeapon(ItemStack itemstack){
			
		String type = itemstack.getType().toString();
		
		if (type.contains("SWORD") ||
			type.contains("HOE") ||
			type.contains("AXE") ||
			type.contains("SPADE")){                                         
			return true;
			}
			return false;
			}
		
		public static int tierNumber(ItemStack itemstack){
			
			String type = itemstack.getType().toString();
			
        	if (type.contains("WOOD") || type.contains("LEATHER") ){return 0;}
        	if (type.contains("STONE") || type.contains("CHAINMAIL") ){return 1;}
        	if (type.contains("IRON")){return 2;}
        	if (type.contains("DIAMOND")){return 3;}
        	if (type.contains("GOLD")){return 4;}
        	
        	return -1;
			
		}
		
		public static int loreEquals(List<String> lores, String string){
			
			for (int i = 0 ; i< lores.size() ; i++){
		    String lore = ChatColor.stripColor(lores.get(i)).replaceAll("[^A-Za-z]", "");
				if (lore.equals(string)){					
					return i;
				}
				
			}
			
			return -1;
		}
		
	public static int loreContains(List<String> lores, String string){
			
			for (int i = 0 ; i< lores.size() ; i++){
		    String lore = ChatColor.stripColor(lores.get(i)).replaceAll("[^A-Za-z]", "");
				if (lore.contains(string)){					
					return i;
				}
				
			}
			
			return -1;
		}
		
	public static String giveLoreEquals(List<String> lores, String string){
			
			for (int i = 0 ; i< lores.size() ; i++){
			    String lore = ChatColor.stripColor(lores.get(i)).replaceAll("[^A-Za-z]", "");
				if (lore.equals(string)){					
					return lores.get(i);
				}
				
			}
			
			return "";
		}
		
		
	public static String giveLoreContains(List<String> lores, String string){
		
		for (int i = 0 ; i< lores.size() ; i++){
		    String lore = ChatColor.stripColor(lores.get(i)).replaceAll("[^A-Za-z]", "");
			if (lore.contains(string)){					
				return lores.get(i);
			}
			
		}
		
		return "";
	}
	
	
		public static int getNumber(String string){
			
			string = ChatColor.stripColor(string).replaceAll("[^0-9]", "");
			if (string.length() > 0){
			return Integer.parseInt(string);			
			}
			return -1;
		}
		
	public static String getText(String string){
			
			string = ChatColor.stripColor(string).replaceAll("[^A-Za-z]", "");
			if (string.length() > 0){
			return string;			
			}
			return "";
		}
		
		
		
		public static int getFirstNumber(String string){
			
			string = ChatColor.stripColor(string).replaceAll("[^0-9/]", "");				
	           
	           String firstNumber = string.split("/")[0];    
	           
	           if ( firstNumber != null){
	        	   return Integer.parseInt(firstNumber);
	           }          
	          
			return -1;
		}
		
		public static int getSecondNumber(String string){
			
			string = ChatColor.stripColor(string).replaceAll("[^0-9/]", "");				
	           
	           String secondNumber = string.split("/")[1];    
	           
	           if ( secondNumber != null){
	        	   return Integer.parseInt(secondNumber);
	           }          
	          
			return -1;
		}
		
	

	public static String warningMessage(String string){
		
		return ChatColor.RED + "" + ChatColor.BOLD + string;
		}
		
		
	public static String succeedMessage(String string){
			
			return ChatColor.GREEN + "" + ChatColor.BOLD + string;
		}
		
	public static String infoMessage(String string){
		
		return ChatColor.AQUA + "" + ChatColor.BOLD + string;
	}

		
	
	public static String[] randomRarity(int[] minValues, int tier){
		Random ran = new Random();       	   	   	    	
    	
    	int rarityRoll = ran.nextInt(100);
    	int statNumber;
    	String rarity = null;
    	int num = minValues[tier];
    	
    	if (rarityRoll > 85){
    	 rarity=ChatColor.YELLOW + "Rarity: Ancient";
    	 statNumber = ran.nextInt(num)+num*4;                	
    	}
    	else if (rarityRoll > 75){
    	 rarity=ChatColor.LIGHT_PURPLE + "Rarity: Unique";
    	 statNumber = ran.nextInt(num)+num*3;
    	}
    	else if (rarityRoll > 50){
    	 rarity=ChatColor.AQUA + "Rarity: Rare";
    	 statNumber = ran.nextInt(num)+num*2;
    	}
    	else if (rarityRoll > 25){
    	 rarity=ChatColor.GREEN + "Rarity: Uncommon";
    	 statNumber = ran.nextInt(num)+num;
    	}
    	else{rarity=ChatColor.GRAY + "Rarity: Common";
    	 statNumber = ran.nextInt(num) + 1;
    	}
    	
    	String[] result = {rarity, Integer.toString(statNumber)}; 
    	
    	return result;
    	
    	}
	
	
	
	
	

	
	
	   
    public static void circleParticles(Player player, double radius, int particles, Effect effect, int height){    	
      
    	for (double t = 0 ; t < Math.PI * 8; t += Math.PI/16 ){
                    Location loc = player.getLocation(); 
                            
                            double x = radius * Math.cos(t);
                            double y = Math.sin(height);
                            double z = radius * Math.sin(t);
                            
                            loc.add(x, y, z);
                            loc.getWorld().playEffect(loc, effect , particles);  
                            loc.subtract(x, y, z);                                   
                    
                    }     


    }
	
    
    
   
    public static void succeedAnimation(Player event){
    	
    	Location loc = event.getPlayer().getLocation();
    	
            final Player player = event.getPlayer();   
            	
            	circleParticles(player, 4, 66, Effect.WITCH_MAGIC,1);
            	circleParticles(player, 3, 55, Effect.WITCH_MAGIC,2);
            	circleParticles(player, 2, 44, Effect.WITCH_MAGIC,3);
            	circleParticles(player, 1, 22, Effect.WITCH_MAGIC,4);           	
            	    
            	player.playSound(loc, Sound.BLOCK_ANVIL_USE , 5 , 2);      	
            	
            }
                 
            
            
    public static void repairAnimation(Player event){
    	
    	Location loc = event.getPlayer().getLocation();
    	
            final Player player = event.getPlayer();   
            	
            	circleParticles(player, 4, 33, Effect.HAPPY_VILLAGER,1);
            	circleParticles(player, 3, 22, Effect.HAPPY_VILLAGER,2);
            	circleParticles(player, 2, 11, Effect.HAPPY_VILLAGER,3);
            	circleParticles(player, 1, 5, Effect.HAPPY_VILLAGER,4);           	
            	    
            	player.playSound(loc, Sound.BLOCK_ANVIL_HIT , 10 , 1);    	
            	
            }
                 
    
	
	
	
	
	
}
