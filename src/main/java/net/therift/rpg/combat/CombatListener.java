package net.therift.rpg.combat;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import net.therift.chat.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.lang.reflect.InvocationTargetException;

public class CombatListener implements Listener {

    /**
     * Event to handle all custom attacks
     * @param e the event
     */
    @EventHandler (priority = EventPriority.LOWEST)
    public void customAttackEvent(PlayerInteractEvent e) {
        if ((e.getHand() == EquipmentSlot.HAND) &&
                (e.getAction() == Action.LEFT_CLICK_AIR ||
                        e.getAction() == Action.LEFT_CLICK_BLOCK)) {
            e.setCancelled(true);
            ChatUtils.so("&aLogged Interact Event");
            //sendAttackAnimation(e.getPlayer(), e.getPlayer());
        }
    }

    /**
     * Send an attack animation to player
     * @param p the player
     * @param swinger the player to animate
     */
    public void sendAttackAnimation(Player p, Player swinger) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer attack = manager.createPacket(PacketType.Play.Client.ARM_ANIMATION);
        // Set attack fields
        try {
            manager.sendServerPacket(p, attack);
        } catch (InvocationTargetException invocationTargetException) {
            ChatUtils.so("&c[Error] Failed to play ARM ANIMATION packet for &f" + p);
        }
    }

}
