package net.therift.rpg.combat.melee.styles;

import net.therift.chat.ChatUtils;
import net.therift.rpg.combat.melee.MeleeStyle;
import net.therift.util.ParticleLib;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class BloodborneVengeance extends MeleeStyle {

    public BloodborneVengeance(Player p) {
        super("Bloodborne Vengeance", "&4Bloodborne Vengeance", 4, new int[]{1500, 1200, 1500, 2000}, 1500, p);
        // {time between 4 and 1, time between 1 and 2, time between 2 and 3, time between 3 and 4}
    }

    /**
     * Attack if valid
     * @return whether or not the attack was valid
     */
    public boolean attack() {
        if (super.attack()) {
            Player p = getPlayer();
            if (getHitCount() == 1) {
                p.sendMessage("1");
                ParticleLib.drawHorizontalArc(p.getLocation(), 60, 5, Particle.CRIT);
            }
            if (getHitCount() == 2) {
                p.sendMessage("2");
            }
            if (getHitCount() == 3) {
                p.sendMessage("3");
            }
            if (getHitCount() == 4) {
                p.sendMessage("4");
            }
            return true;
        }
        return false;
    }
}
