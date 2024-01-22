package com.github.beadieststar64.plugins.bsseries.bsmanager;

import com.github.beadieststar64.plugins.bsseries.bscore.API.FileManager;
import com.github.beadieststar64.plugins.bsseries.bscore.API.YamlReader;
import com.github.beadieststar64.plugins.bsseries.bscore.AbstractFileManager;
import com.github.beadieststar64.plugins.bsseries.bscore.AbstractYamlReader;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class BSManager extends JavaPlugin {

    public static AbstractFileManager afm;
    public static AbstractYamlReader ayr;
    public static AbstractYamlReader messenger;

    @Override
    public void onEnable() {
        afm = new AbstractFileManager(this);
        afm.managerInitialize(getDataFolder(), "RequestFiles.txt");
        Message message = new Message(this);
        ayr = new AbstractYamlReader(this);
        messenger = new AbstractYamlReader(this, "", "message.yml");

        //register service manager
        getServer().getServicesManager().register(FileManager.class, afm, this, ServicePriority.Normal);
        getServer().getServicesManager().register(YamlReader.class, ayr, this, ServicePriority.Normal);

        getServer().getConsoleSender().sendMessage(message.convertMessage("HookMassage", new Object[]{"FileManager", this.getDescription().getName()}));
        getServer().getConsoleSender().sendMessage(message.convertMessage("HookMassage", new Object[]{"YamlReader", this.getDescription().getName()}));
    }

    @Override
    public void onDisable() {
        getServer().getServicesManager().unregister(FileManager.class, afm);
        getServer().getServicesManager().unregister(YamlReader.class, ayr);
    }


}
