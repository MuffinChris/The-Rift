package net.therift.server;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.therift.Main;
import net.therift.chat.ChatUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

public class MotdListener implements Listener {

    /**
     * Event to handle MOTD for server
     * @param e event
     */
    @EventHandler
    public void ping(ServerListPingEvent e) {
        File pFile = new File(Main.PLUGIN_PATH + "motd.yml");
        FileConfiguration pData = YamlConfiguration.loadConfiguration(pFile);
        if (!pData.contains("LineOne")) {
            pData.set("LineOne", "&d&l          The Rift");
            pData.set("LineTwo", "&f            RPG Survival");
            try {
                pData.save(pFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            String content = ChatUtils.color(pData.getString("LineOne") + "\n" + pData.getString("LineTwo"));
            TextComponent motd = Component.text(content);
            e.motd(motd);
        }

    }

}
