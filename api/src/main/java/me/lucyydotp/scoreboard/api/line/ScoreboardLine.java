package me.lucyydotp.scoreboard.api.line;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * A line on a scoreboard.
 */
public interface ScoreboardLine {
    /**
     * The "player name" to show.
     */
    Component name();

    /**
     * A right-aligned component to show next to the player name.
     * Left blank if null.
     */
    @Nullable Component score();

    /**
     * Creates a new line from a name and score.
     *
     * @param name  the name
     * @param score the score
     * @return a new scoreboard line
     */
    @Contract(value = "_, _ -> new", pure = true)
    static ScoreboardLine line(Component name, @Nullable Component score) {
        return new ScoreboardLineImpl(name, score);
    }

    /**
     * Creates a new line from a name, with a blank score.
     *
     * @param name  the name
     * @return a new scoreboard line
     */
    @Contract(value = "_ -> new", pure = true)
    static ScoreboardLine line(Component name) {
        return line(name, null);
    }
}
