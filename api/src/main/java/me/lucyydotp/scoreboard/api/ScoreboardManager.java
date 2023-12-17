package me.lucyydotp.scoreboard.api;

import me.lucyydotp.scoreboard.api.board.Scoreboard;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identified;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Manages which scoreboards are assigned to players.
 *
 * @param <P> the player type
 */
public interface ScoreboardManager<P extends Audience & Identified> {
    /**
     * Assigns a scoreboard to a player, or removes it if null.
     *
     * @param player the player
     * @param board  the scoreboard to assign to the player
     */
    void setScoreboard(P player, @Nullable Scoreboard<? super P> board);

    /**
     * Temp method to refresh all scoreboards for everyone
     */
    @ApiStatus.Internal
    void update();
}
