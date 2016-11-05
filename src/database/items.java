package database;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import combat.stats;
import org.bukkit.ChatColor;





public class items {
	

	static public ItemStack T1Scrap = new ItemStack(Material.LEATHER);
	static public ItemStack T2Scrap = new ItemStack(Material.IRON_FENCE);
	static public ItemStack T3Scrap = new ItemStack(Material.INK_SACK,1,(short) 7);
	static public ItemStack T4Scrap = new ItemStack(Material.INK_SACK,1,(short) 6);
	static public ItemStack T5Scrap = new ItemStack(Material.INK_SACK,1,(short) 14);	
	static public String[] scrapLore = {ChatColor.GRAY + "Click on an item to repair it",ChatColor.GRAY +  "Repairs 100 durability!"};

	static public ItemStack acceptTrade = new ItemStack(Material.INK_SACK,1,(short) 10);
	static public String[] acceptTradeLore = {ChatColor.GREEN + "Press to sell items!"};
	
	
  public static void main() {	

   	T1Scrap = makeItem(T1Scrap, ChatColor.GRAY + "Scrap: Repairs Tier 1 equipment", scrapLore);
   	T2Scrap = makeItem(T2Scrap, ChatColor.GREEN + "Scrap: Repairs Tier 2 equipment", scrapLore);
   	T3Scrap = makeItem(T3Scrap, ChatColor.AQUA + "Scrap: Repairs Tier 3 equipment", scrapLore);
   	T4Scrap = makeItem(T4Scrap, ChatColor.LIGHT_PURPLE + "Scrap: Repairs Tier 4 equipment", scrapLore);
   	T5Scrap = makeItem(T5Scrap, ChatColor.YELLOW + "Scrap: Repairs Tier 5 equipment", scrapLore);
   	acceptTrade = makeItem(acceptTrade, ChatColor.BOLD + "" + ChatColor.RED + "TRADE", acceptTradeLore);
   
		
	
	
	
  }
  
	public static ItemStack makeItem(ItemStack item, String name, String[] lores){
		
		ArrayList<String> lore = new ArrayList<String>();
		
		for (int i = 0 ; i < lores.length ; i++){lore.add(lores[i]);}
		
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		
		meta.setLore(lore);
		
		item.setItemMeta(meta);	
		
		return item;	
	}
  
  
  
	
}
