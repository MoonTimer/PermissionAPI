package me.moontimer.permission.permission;

import com.sun.istack.internal.NotNull;

import java.util.Collection;
import java.util.List;
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
