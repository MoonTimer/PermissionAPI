package me.moontimer.permissionapi.permission.impl;

import me.moontimer.permissionapi.permission.PermissionProvider;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class GroupManagerProvider implements PermissionProvider {
    @Override
    public String getProviderName() {
        return "GroupManager";
    }

    @Override
    public boolean has(UUID uuid, String permission) {
        final AnjoPermissionsHandler handler = new GroupManager().getWorldsHolder().getWorldPermissions(Bukkit.getOfflinePlayer(uuid).getPlayer());
        return handler != null && handler.has(Bukkit.getOfflinePlayer(uuid).getPlayer(), permission);
    }

    @Override
    public void add(UUID uuid, String permission) {
        final OverloadedWorldHolder handler = new GroupManager().getWorldsHolder().getWorldData(Bukkit.getOfflinePlayer(uuid).getPlayer());
        if (handler == null) return;
        handler.getUser(Bukkit.getOfflinePlayer(uuid).getPlayer().getName()).addPermission(permission);
    }

    @Override
    public void remove(UUID uuid, String permission) {
        final OverloadedWorldHolder handler = new GroupManager().getWorldsHolder().getWorldData(Bukkit.getOfflinePlayer(uuid).getPlayer());
        if (handler == null) return;
        handler.getUser(Bukkit.getOfflinePlayer(uuid).getPlayer().getName()).removePermission(permission);
    }

    @Override
    public Set<String> getGroups(UUID uuid) {

        final AnjoPermissionsHandler handler = new GroupManager().getWorldsHolder().getWorldPermissions(Bukkit.getOfflinePlayer(uuid).getPlayer());
        return (Set<String>) Arrays.asList(handler.getGroups(Bukkit.getOfflinePlayer(uuid).getName()));
    }

    @Override
    public Set<String> getGroups() {
        return null;
    }
}
