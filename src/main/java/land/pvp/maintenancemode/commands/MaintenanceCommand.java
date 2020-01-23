package land.pvp.maintenancemode.commands;

import land.pvp.maintenancemode.MaintenanceModePlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class MaintenanceCommand extends Command {
    private final MaintenanceModePlugin plugin;

    public MaintenanceCommand(MaintenanceModePlugin plugin) {
        super("maintenance", "pvpland.maintenance");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        boolean inMaintenance = !plugin.isServerInMaintenance();
        plugin.setServerInMaintenance(inMaintenance);

        if (inMaintenance) {
            sender.sendMessage(new ComponentBuilder("The server is now in maintenance mode.").color(ChatColor.RED).create());
        } else {
            sender.sendMessage(new ComponentBuilder("The server is no longer in maintenance mode.").color(ChatColor.GREEN).create());
        }
    }
}
