package haveric.dieSheep;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class SheepInteract implements Listener {

    DieSheep plugin;
    public SheepInteract(DieSheep dieSheep) {
        plugin = dieSheep;
    }

    @EventHandler
    public void sheepShearEvent(PlayerShearEntityEvent event) {
        if (event.getEntity().getType() == EntityType.SHEEP) {
            Sheep sheep = (Sheep) event.getEntity();

            if (Config.isEnabled(sheep.getWorld().getName())) {
                sheep.setColor(DyeColor.WHITE);
                Location loc = sheep.getLocation();

                Wolf wolf = (Wolf) loc.getWorld().spawn(loc, Wolf.class);
                wolf.setAngry(true);
                wolf.damage(0, event.getPlayer());
            }
        }
    }
    @EventHandler
    public void sheepHurtEvent(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.SHEEP) {
            Sheep sheep = (Sheep) event.getEntity();

            if (Config.isEnabled(sheep.getWorld().getName())) {
                sheep.setColor(DyeColor.WHITE);
            }
        }
    }

    @EventHandler
    public void sheepDieEvent(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.SHEEP) {
            Sheep sheep = (Sheep) event.getEntity();

            if (Config.isEnabled(sheep.getWorld().getName())) {
                sheep.getWorld().createExplosion(sheep.getLocation(), Config.getExplosionDamage());
                sheep.setFallDistance(100);
            }
        }
    }

    @EventHandler
    public void sheepDyeEvent(SheepDyeWoolEvent event) {
        if (event.getEntityType() == EntityType.SHEEP) {
            Sheep sheep = (Sheep) event.getEntity();

            if (Config.isEnabled(sheep.getWorld().getName())) {
                event.setCancelled(true);
                sheep.setColor(DyeColor.WHITE);
                sheep.getWorld().createExplosion(event.getEntity().getLocation(), Config.getExplosionDamage());
                sheep.setFallDistance(100);
            }
        }
    }
}
