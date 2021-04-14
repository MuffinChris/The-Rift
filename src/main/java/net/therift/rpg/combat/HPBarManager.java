package net.therift.rpg.combat;

import com.google.inject.Inject;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.therift.Main;
import net.therift.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

public class HPBarManager {

    private Main plugin;

    @Inject
    public HPBarManager(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Send an HP Action Bar to a player
     * @param p the player
     */
    public void sendHp(Player p) {
        if (plugin.getRPGUtils().getRPGPlayer(p) != null) {
            DecimalFormat dformatZero = new DecimalFormat("#");
            DecimalFormat dformatTwo = new DecimalFormat("#.##");

            String barText = "&c❤ " + dformatTwo.format(p.getHealth())
                    + "    &7" + dformatZero.format(p.getLocation().getX())
                    + " &f" + yawToString(p.getLocation().getYaw()) + " &7"
                    + dformatZero.format(p.getLocation().getZ()) + "    &b✦ " + "0";
            TextComponent barComponent = Component.text(ChatUtils.color(barText));

            p.sendActionBar(barComponent);

            //p.setLevel(rp.getLevel());
            //p.setExp(Math.min(Math.max((float) rp.getPercent(), 0.0f), 1.0F));
        }
    }

    /**
     * Periodically update the action bar of all players
     */
    public void sendHpPeriodic() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    sendHp(p);
                }
            }
        }.runTaskTimer(plugin, 5L, 4L);
    }

    /**
     * Code ripped from SpigotMC to determine direction in Compass format
     */
    private static final BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
    private static final BlockFace[] radial = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };

    /**
     * Return a BlockFace direction given yaw
     * @param yaw yaw
     * @param useSubCardinalDirections
     * @return BlockFace
     */
    public static BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
        if (useSubCardinalDirections)
            return radial[Math.round(yaw / 45f) & 0x7].getOppositeFace();

        return axis[Math.round(yaw / 90f) & 0x3].getOppositeFace();
    }

    /**
     * Get Compass string from BlockFace
     * @param yaw yaw
     * @return compass string
     */
    public static String yawToString(float yaw) {
        BlockFace bf = radial[Math.round(yaw / 45f) & 0x7].getOppositeFace();
        if (bf == BlockFace.NORTH) {
            return "N";
        }
        if (bf == BlockFace.NORTH_WEST) {
            return "NW";
        }
        if (bf == BlockFace.NORTH_EAST) {
            return "NE";
        }
        if (bf == BlockFace.SOUTH) {
            return "S";
        }
        if (bf == BlockFace.SOUTH_EAST) {
            return "SE";
        }
        if (bf == BlockFace.SOUTH_WEST) {
            return "SW";
        }
        if (bf == BlockFace.EAST) {
            return "E";
        }
        if (bf == BlockFace.WEST) {
            return "W";
        }
        return "";
    }

}
