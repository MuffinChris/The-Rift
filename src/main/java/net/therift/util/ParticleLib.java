package net.therift.util;

import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleLib {

    /**
     * Draw a horizontal arc around a center location
     * @param center center location
     * @param degrees degrees to arc
     * @param particle particle type
     */
    public static void drawHorizontalArc(Location center, int degrees, double radius, Particle particle) {
        for (int degree = -degrees/2; degree < degrees/2; degree++) {
            double radians = Math.toRadians(degree);
            double x = Math.cos(radians) * radius;
            double z = Math.sin(radians) * radius;
            center.getWorld().spawnParticle(particle, center.clone().add(x,0,z), 1, 0, 0, 0, 0);
        }
    }

}
