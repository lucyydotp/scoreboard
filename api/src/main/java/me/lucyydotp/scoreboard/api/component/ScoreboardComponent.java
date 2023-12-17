package me.lucyydotp.scoreboard.api.component;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A component as part of a scoreboard.
 *
 * @param <P> the player type
 */
public interface ScoreboardComponent<P extends Audience & Identified> extends Comparable<ScoreboardComponent<?>> {
    /**
     * The scoreboard component's unique key.
     */
    Key key();

    /**
     * Whether this component should show for a player.
     *
     * @param player the player to check
     */
    boolean shouldShowFor(P player);

    /**
     * The component's priority. Higher priorities are first in the scoreboard.
     */
    int priority();

    /**
     * The lines that make up this scoreboard for a player.
     *
     * @param player the player to get the scoreboard lines for
     */
    List<ScoreboardLine> lines(P player);

    /**
     * Compares components by {@link #priority}.
     */
    @Override
    default int compareTo(@NotNull ScoreboardComponent<?> o) {
        return o.priority() - this.priority();
    }
}
