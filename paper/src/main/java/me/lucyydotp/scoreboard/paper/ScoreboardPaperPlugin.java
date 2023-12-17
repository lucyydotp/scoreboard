package me.lucyydotp.scoreboard.paper;

import io.papermc.paper.adventure.PaperAdventure;
import me.lucyydotp.scoreboard.nms.PlatformAdapter;
import me.lucyydotp.scoreboard.nms.ScoreboardManagerImpl;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ScoreboardPaperPlugin extends JavaPlugin implements PlatformAdapter<Player> {

    @Override
    public @Nullable Player getNativePlayer(UUID uuid) {
        return Bukkit.getPlayer(uuid);
    }

    @Override
    public ServerPlayer getPlayer(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    @Override
    public Component fromAdventure(net.kyori.adventure.text.Component adventureComponent) {
        return PaperAdventure.asVanilla(adventureComponent);
    }

    public final ScoreboardManagerImpl<Player> manager = new ScoreboardManagerImpl<>(this);

    @Override
    public void onEnable() {
        getServer().getCommandMap().register("sbtest", new TestCommand(this));
    }
}
