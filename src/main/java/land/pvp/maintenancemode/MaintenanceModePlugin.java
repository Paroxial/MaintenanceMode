package land.pvp.maintenancemode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import land.pvp.maintenancemode.commands.HubCommand;
import land.pvp.maintenancemode.commands.MaintenanceCommand;
import land.pvp.maintenancemode.listeners.MaintenanceListener;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MaintenanceModePlugin extends Plugin {
    private Configuration configuration;
    @Getter
    @Setter
    private boolean serverInMaintenance;

    @Override
    public void onEnable() {
        PluginManager pluginManager = getProxy().getPluginManager();

        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }

            File file = new File(getDataFolder(), "config.yml");

            if (!file.exists()) {
                try (InputStream in = getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            this.serverInMaintenance = configuration.getBoolean("enabled");
        } catch (IOException e) {
            e.printStackTrace();
        }

        pluginManager.registerListener(this, new MaintenanceListener(this));
        pluginManager.registerCommand(this, new MaintenanceCommand(this));
        pluginManager.registerCommand(this, new HubCommand());
    }

    @Override
    public void onDisable() {
        configuration.set("enabled", serverInMaintenance);

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
