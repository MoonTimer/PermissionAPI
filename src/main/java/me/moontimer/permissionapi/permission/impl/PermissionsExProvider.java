package me.moontimer.permissionapi.permission.impl;

import me.moontimer.permissionapi.permission.PermissionProvider;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.*;

public class PermissionsExProvider implements PermissionProvider {
    @Override
    public String getProviderName() {
        return "PermissionsEx";
    }

    @Override
    public boolean has(UUID uuid, String permission) {
        PermissionUser user = PermissionsEx.getPermissionManager().getUser(uuid);
        return user != null && user.has(permission);
    }

    @Override
    public void add(UUID uuid, String permission) {
        PermissionUser user = PermissionsEx.getPermissionManager().getUser(uuid);
        user.addPermission(permission);
        user.save();
    }

    @Override
    public void remove(UUID uuid, String permission) {
        PermissionUser user = PermissionsEx.getPermissionManager().getUser(uuid);
        user.removePermission(permission);
        user.save();
    }

    @Override
    public Set<String> getGroups(UUID uuid) {
        PermissionUser user = PermissionsEx.getPermissionManager().getUser(uuid);
        return user != null ? (Set<String>) user.getParentIdentifiers() : Collections.emptySet();
    }

    @Override
    public Set<String> getGroups() {
        return (Set<String>) PermissionsEx.getPermissionManager().getGroupNames();
    }
}
