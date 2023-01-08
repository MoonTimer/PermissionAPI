package me.moontimer.permissionapi.permission;


import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public interface PermissionProvider {
    @NotNull
    String getProviderName();

    boolean has(@NotNull UUID uuid, @NotNull String permission);

    void add(@NotNull UUID uuid, @NotNull String permission);

    void remove(@NotNull UUID uuid, @NotNull String permission);

    @NotNull
    Set<String> getGroups(@NotNull UUID uuid);

    @NotNull
    Set<String> getGroups();
}

