package me.moontimer.permissionapi.permission.impl;

import me.moontimer.permissionapi.permission.PermissionProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.platform.PlayerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

public class LuckPermsProvider implements PermissionProvider {
    @Override
    public String getProviderName() {
        return "LuckPerms";
    }

    @Override
    public boolean has(UUID uuid, String permission) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        return offlinePlayer != null && offlinePlayer.getPlayer().hasPermission(permission);
    }

    @Override
    public void add(UUID uuid, String permission) {
        Node node = Node.builder(permission).build();
        net.luckperms.api.LuckPermsProvider.get().getUserManager().modifyUser(uuid, (User user) -> {
            user.data().add(node);
            net.luckperms.api.LuckPermsProvider.get().getUserManager().saveUser(user);
        });
    }

    @Override
    public void remove(UUID uuid, String permission) {
        Node node = Node.builder(permission).build();
        net.luckperms.api.LuckPermsProvider.get().getUserManager().modifyUser(uuid, (User user) -> {
            user.data().remove(node);
            net.luckperms.api.LuckPermsProvider.get().getUserManager().saveUser(user);
        });
    }

    @Override
    public Set<String> getGroups(UUID uuid) {
        Player player = Bukkit.getOfflinePlayer(uuid).getPlayer();
        PlayerAdapter<Player> playerAdapter = net.luckperms.api.LuckPermsProvider.get().getPlayerAdapter(Player.class);
        User user = playerAdapter.getUser(player);
        @NotNull @Unmodifiable Collection<Group> groups = user.getInheritedGroups(playerAdapter.getQueryOptions(player));
        Set<String> groupList = new HashSet<>(Collections.emptySet());
        for (Group group : groups) {
            groupList.add(group.getName());
        }
        return groupList;
    }

    @Override
    public Set<String> getGroups() {
        Set<String> groupNames = new HashSet<>();
        for (Group group : net.luckperms.api.LuckPermsProvider.get().getGroupManager().getLoadedGroups()) {
            groupNames.add(group.getName());
        }
        return groupNames;
    }
}
