package haveric.dieSheep;

import java.util.List;

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
import org.bukkit.inventory.ItemStack;

public class SheepInteract implements Listener {

    public SheepInteract() {

    }

    @EventHandler
    public void sheepShearEvent(PlayerShearEntityEvent event) {
        if (event.getEntity().getType() == EntityType.SHEEP) {
            Sheep sheep = (Sheep) event.getEntity();

            if (Config.isEnabled(sheep.getWorld().getName())) {
                sheep.setColor(DyeColor.WHITE);
                if (!Config.getDropWool()) {
                    event.setCancelled(true);
                    sheep.setSheared(true);
                }

                String shearType = Config.getShearType();
                if (shearType.equals(Config.TYPE_WOLF)) {
                    spawnWolf(sheep.getLocation());
                } else if (shearType.equals(Config.TYPE_EXPLOSION)) {
                    spawnExplosion(sheep, Config.getShearExplosionPower());
                }
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
                if (!Config.getDropWool()) {
                    List<ItemStack> drops = event.getDrops();

                    for (int i = 0; i < drops.size(); i++) {
                        if (DSTools.isWool(drops.get(i).getType())) {
                            drops.remove(i);
                            i--;
                        }
                    }
                }

                String dieType = Config.getDieType();
                if (dieType.equals(Config.TYPE_EXPLOSION)) {
                    spawnExplosion(sheep, Config.getDieExplosionPower());
                }
            }
        }
    }

    @EventHandler
    public void sheepDyeEvent(SheepDyeWoolEvent event) {
        if (event.getEntityType() == EntityType.SHEEP) {
            Sheep sheep = event.getEntity();

            if (Config.isEnabled(sheep.getWorld().getName())) {
                event.setCancelled(true);
                sheep.setColor(DyeColor.WHITE);

                String dyeType = Config.getDyeType();
                if (dyeType.equals(Config.TYPE_EXPLOSION)) {
                    spawnExplosion(sheep, Config.getDyeExplosionPower());
                }
            }
        }
    }

    public void spawnWolf(Location loc) {
        loc.getWorld().spawn(loc, Wolf.class);
    }

    public void spawnExplosion(Sheep sheep, double amount) {
        sheep.getWorld().createExplosion(sheep.getLocation(), (float) amount);
    }
}
