package land.pvp.maintenancemode.listeners;

import land.pvp.maintenancemode.MaintenanceModePlugin;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@RequiredArgsConstructor
public class MaintenanceListener implements Listener {
    private final MaintenanceModePlugin plugin;

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        if (!plugin.isServerInMaintenance()) {
            return;
        }

        ServerPing response = event.getResponse();

        response.setVersion(new ServerPing.Protocol(ChatColor.RED + "Whitelisted", 1));
        response.setPlayers(new ServerPing.Players(0, 0, new ServerPing.PlayerInfo[]{
                new ServerPing.PlayerInfo(ChatColor.AQUA + "Follow " + ChatColor.LIGHT_PURPLE + "@PvP_Land" + ChatColor.AQUA + " on Twitter for updates!", "")
        }));
    }
}
