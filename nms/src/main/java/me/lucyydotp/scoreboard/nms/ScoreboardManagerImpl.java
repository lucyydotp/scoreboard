package me.lucyydotp.scoreboard.nms;

import me.lucyydotp.scoreboard.api.ScoreboardManager;
import me.lucyydotp.scoreboard.api.board.Scoreboard;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.identity.Identity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.numbers.BlankFormat;
import net.minecraft.network.chat.numbers.FixedFormat;
import net.minecraft.network.protocol.game.ClientboundResetScorePacket;
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardManagerImpl<P extends Audience & Identified> implements ScoreboardManager<P> {
    private final Map<UUID, Scoreboard<? super P>> boards = new HashMap<>();
    private final PlatformAdapter<P> adapter;
    private final Map<UUID, Integer> lastSize = new HashMap<>();

    private static final String SCOREBOARD_NAME = "lucyydotp_scoreboard";

    public ScoreboardManagerImpl(PlatformAdapter<P> adapter) {
        this.adapter = adapter;
    }

    private UUID uuid(P player) {
        return player.get(Identity.UUID).get();
    }

    @Override
    public void setScoreboard(P player, @Nullable Scoreboard<? super P> board) {
        if (board == null) {
            boards.remove(uuid(player));
        } else {
            boards.put(uuid(player), board);
        }
    }

    private void createObjective(ServerPlayer player) {
        final var objective = new Objective(
                null,
                SCOREBOARD_NAME,
                ObjectiveCriteria.DUMMY,
                Component.empty(),
                ObjectiveCriteria.RenderType.INTEGER,
                true,
                new BlankFormat()
        );

        player.connection.send(new ClientboundSetObjectivePacket(objective, 0));

        player.connection.send(new ClientboundSetDisplayObjectivePacket(
                DisplaySlot.SIDEBAR,
                objective
        ));
    }

    private void update(P nativePlayer) {
        final var player = adapter.getPlayer(nativePlayer);
        if (player == null) return;

        var size = lastSize.get(player.getUUID());

        if (size == null) {
            createObjective(player);
            size = 0;
        }

        final var board = boards.get(player.getUUID());
        if (board == null) return; // todo: remove objective

        final var lines = board.components()
                .stream()
                .sorted()
                .filter(section -> section.shouldShowFor(nativePlayer))
                .flatMap(it -> it.lines(nativePlayer).stream())
                .toList();

        for (int i = 0; i < lines.size(); i++) {
            final var line = lines.get(i);

            final var score = line.score();
            final var scoreFormat = score == null ? new BlankFormat() : new FixedFormat(adapter.fromAdventure(score));

            player.connection.send(
                    new ClientboundSetScorePacket(
                            new UUID(0, i).toString(),
                            SCOREBOARD_NAME,
                            i,
                            adapter.fromAdventure(line.name()),
                            scoreFormat
                    )
            );
        }
        lastSize.put(player.getUUID(), lines.size());

        if (size > lines.size()) {
            for (int i = 0; i < size - lines.size(); i++) {
                player.connection.send(new ClientboundResetScorePacket(
                        new UUID(0, lines.size() + i).toString(),
                        SCOREBOARD_NAME
                ));
            }
        }
    }

    @Override
    public void update() {
        boards.forEach((uuid, scoreboard) -> update(adapter.getNativePlayer(uuid)));
    }
}
