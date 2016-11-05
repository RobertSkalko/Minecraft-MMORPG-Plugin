package Main;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class listeners implements Listener {
	
		

		// stops enchants/maps from being thrown around
		@EventHandler
		public void onMapClick(PlayerInteractEvent event) {

			if (event.getItem() == null) {return;}
			if (event.getItem().getType() == null) {return;}
			if (event.getItem().getType() == Material.EMPTY_MAP) {
				Player player = (Player) event.getPlayer();
				event.setCancelled(true);
				player.updateInventory();
			}

		}

		// stops mobs from burning
		@EventHandler
		public void onMobFire(EntityCombustEvent event) {
			if (!event.getEntityType().toString().contains("PLAYER")) {
				event.setCancelled(true);
			}
		}
		
		
		

			
			
	
		
		

}
