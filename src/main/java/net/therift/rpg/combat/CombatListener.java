package net.therift.rpg.combat;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.google.inject.Inject;
import net.therift.Main;
import net.therift.chat.ChatUtils;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.lang.reflect.InvocationTargetException;

public class CombatListener implements Listener {

    private Main plugin;

    @Inject
    public CombatListener(Main plugin) {
        this.plugin = plugin;
        customAttackEventHit();
    }

    /**
     * Event to handle all custom attacks when target is "missed"
     * Note: As of 1.16 PlayerInteractEvent is bugged due to the animation being played
     * when dropping an item also triggers the interact event.
     * @param e the event
     */
    @EventHandler (priority = EventPriority.LOW)
    public void customAttackEvent(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.HAND && e.getAction() == Action.LEFT_CLICK_AIR) {
            if (isHoldingWeapon(e.getPlayer())) {
                e.setCancelled(true);
                generateAttack(e.getPlayer());
            }
        }
    }

    /**
     * Handle all custom attacks when target is hit
     */
    public void customAttackEventHit() {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.LOW, PacketType.Play.Client.USE_ENTITY) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                if (e.getPacketType() == PacketType.Play.Client.USE_ENTITY) {
                    if (isHoldingWeapon(e.getPlayer())) {
                        EnumWrappers.EntityUseAction useAction = e.getPacket().getEntityUseActions().read(0);
                        e.setCancelled(true);
                        generateAttack(e.getPlayer());
                    }
                }
            }
        });
    }

    /**
     * Create attack particles
     * @param p the player
     */
    public void generateAttack(Player p) {
        p.spawnParticle(Particle.FLAME, p.getLocation(), 30);
    }

    /**
     * Check if the player is holding a valid weapon
     * @param p the player
     * @return true if holding a valid weapon
     */
    public boolean isHoldingWeapon(Player p) {
        // TODO: Implement
        return true;
    }

}
