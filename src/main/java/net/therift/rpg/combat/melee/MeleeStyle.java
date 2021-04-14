package net.therift.rpg.combat.melee;

import de.tr7zw.nbtapi.*;
import net.therift.chat.ChatUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MeleeStyle {

    // The name of the style
    private String name;
    // The color code included name of the style
    private String fancyName;

    // Number of attacks in a combo set
    private int numAttacks;

    // Time in milliseconds between each hit
    private int[] hitCooldown;

    // Time to reset combo
    private int timeout;

    private Player player;

    // store the number of hits
    private int hitCount;

    // the last time player successfully attacked
    private long lastHit;

    public MeleeStyle(String name, String fancyName, int numAttacks, int[] hitCooldown, int timeout, Player player) {
        this.name = name;
        this.fancyName = fancyName;
        this.numAttacks = numAttacks;
        this.player = player;
        this.hitCooldown = hitCooldown;
        this.timeout = timeout;
        resetHitCount();
        lastHit = 0;
    }

    /**
     * Return the regular name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the name with color codes
     * @return color coded name
     */
    public String getFancyName() {
        return fancyName;
    }

    /**
     * Return number of attacks in a combo
     * @return num attacks
     */
    public int getNumAttacks() {
        return numAttacks;
    }

    /**
     * Return the player using the style
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Return the time between hits for each hit
     * @return hit cooldown in milliseconds
     */
    public int[] getHitCooldown() {
        return hitCooldown;
    }

    /**
     * Return combo timeout time
     * @return timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Return when the player last successfully attacked
     * @return long time in millis
     */
    public long getLastHit() {
        return lastHit;
    }

    /**
     * Return the hit count
     * @return the hit count
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * Reset the hit count
     */
    public void resetHitCount() {
        hitCount = 0;
        int hitCooldown = getHitCooldown()[0];
        double seconds = (hitCooldown * 1.0) / (1000.0);
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue((1.0) / (seconds));
    }

    public void timeoutHitCount() {
        hitCount = 0;
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0);
    }

    /**
     * Increase the hit count
     */
    public void increaseHitCount() {
        hitCount++;
    }

    /**
     * Perform an attack if cooldown allows
     */
    public boolean attack() {
        int hitCountIndex = hitCount;
        if (hitCountIndex >= getHitCooldown().length) {
            hitCountIndex = 0;
        }
        if (System.currentTimeMillis() - lastHit <= getHitCooldown()[hitCountIndex]) {
            return false;
        }

        increaseHitCount();
        if (hitCount > numAttacks) {
            resetHitCount();
            increaseHitCount();
        }
        lastHit = System.currentTimeMillis();

        // Set the player's attack speed to reflect their hit cooldown
        int newHitCountIndex = hitCountIndex + 1;
        if (newHitCountIndex >= getHitCooldown().length) {
            newHitCountIndex = 0;
        }
        int hitCooldown = getHitCooldown()[newHitCountIndex];
        double seconds = (hitCooldown * 1.0) / (1000.0);
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue((1.0) / (seconds));
        setAttackSpeed(player, 0);

        return true;
    }

    public void setAttackSpeed(Player p, double seconds) {
        ItemStack mainhand = p.getInventory().getItemInMainHand();
        if (!mainhand.getType().isAir()) {
            NBTItem nbti = new NBTItem(mainhand);
            NBTCompoundList attribute = nbti.getCompoundList("AttributeModifiers");
            p.sendMessage(attribute.get(0).getDouble("Amount") + "");
        }
    }

    /**
     * Return whether or not the attack combo should time out
     * @return true if it should timeout
     */
    public boolean shouldTimeout() {
        int hitCountIndex = hitCount;
        if (hitCountIndex >= getHitCooldown().length) {
            hitCountIndex = 0;
        }
        return System.currentTimeMillis() - getLastHit() > getHitCooldown()[hitCountIndex] + getTimeout();
    }

    /**
     * Cleanup fields
     */
    public void close() {
        player = null;
        name = null;
        fancyName = null;
    }

}
