package me.moontimer.permission;

import me.moontimer.permission.permission.PermissionProvider;
import me.moontimer.permission.permission.impl.PermissionsExProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PermissionAPI extends JavaPlugin {

    private static PermissionAPI instance;
    private PermissionProvider permissionProvider;
    private List<PermissionProvider> providers;


    @Override
    public void onLoad() {
        providers = new ArrayList<>();
        providers.add(new PermissionsExProvider());
    }

    @Override
    public void onEnable() {
        instance = this;
        loadProvider();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void loadProvider() {
        List<String> providerNameList = new ArrayList<>();
        providerNameList.add("LuckPerms");
        providerNameList.add("GroupManager");
        providerNameList.add("PermissionsEx");
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (providerNameList.contains(plugin.getName())) {
                permissionProvider = getProviderByName(plugin.getName());
                return;
            }
        }
        permissionProvider = null;
    }

    private PermissionProvider getProviderByName(String name) {
        for (PermissionProvider provider : providers) {
            if (provider.getProviderName().equals(name)) {
                return provider;
            }
        }
        return null;
    }

    public static PermissionAPI getInstance() {
        return instance;
    }

    public PermissionProvider getPermissionProvider() {
        return permissionProvider;
    }
}
