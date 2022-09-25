package plugin.motioncraft.spigot.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.impl.EventFoodLevelChange;
import plugin.motioncraft.user.CommonUser;

public class ListenerHealthFood implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent platformEvent) {}

	@EventHandler
	public void onHeal(EntityRegainHealthEvent platformEvent) {}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent platformEvent) {
		CommonUser user = MotionCraft.getApi().getUserManager().getFromId(platformEvent.getEntity().getUniqueId());
		Event event = new EventFoodLevelChange(user);
		MotionCraft.getApi().getEventManager().post(event);

		if (event.isCancelled())
			platformEvent.setCancelled(true);
	}
}
