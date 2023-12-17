package me.lucyydotp.scoreboard.api.component;

import me.lucyydotp.scoreboard.api.line.ScoreboardLine;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.key.Key;

import java.util.List;

/**
 * A component that shows the same text to everyone.
 */
record FixedComponent<P extends Audience & Identified>(
        Key key,
        int priority,
        List<ScoreboardLine> lines
) implements ScoreboardComponent<P> {
    @Override
    public boolean shouldShowFor(P player) {
        return true;
    }

    @Override
    public List<ScoreboardLine> lines(P player) {
        return lines;
    }
}
