package me.lucyydotp.scoreboard.nms;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identified;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface PlatformAdapter<P extends Audience & Identified> {

    @Nullable P getNativePlayer(UUID uuid);

    ServerPlayer getPlayer(P player);

    Component fromAdventure(net.kyori.adventure.text.Component adventureComponent);
}
