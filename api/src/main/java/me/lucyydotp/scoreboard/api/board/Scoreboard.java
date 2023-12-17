package me.lucyydotp.scoreboard.api.board;

import me.lucyydotp.scoreboard.api.component.ScoreboardComponent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Arrays;
import java.util.List;

/**
 * A scoreboard - a set of {@link ScoreboardComponent}s with a title.
 *
 * @param <P> the player type
 */
public interface Scoreboard<P extends Audience & Identified> {
    /**
     * Gets an unmodifiable view of this scoreboard's components.
     */
    @UnmodifiableView
    List<ScoreboardComponent<? super P>> components();

    /**
     * Attempts to add a component to the scoreboard.
     *
     * @param component the component to add
     * @return {@code true} if the component was added, {@code false} if a component with same key is already registered
     */
    boolean add(ScoreboardComponent<? super P> component);

    /**
     * Removes a component from the scoreboard.
     *
     * @param key the component's key
     * @return {@code true} if a component with that key was removed, {@code false} otherwise
     */
    boolean remove(Key key);

    /**
     * Gets the scoreboard's title.
     */
    Component title();

    /**
     * Sets the scoreboard's title.
     *
     * @param component the title for the scoreboard
     */
    void title(Component component);

    static <P extends Audience & Identified> Scoreboard<P> fixed(Component title, List<ScoreboardComponent<? super P>> components) {
        return new FixedBoard<>(title, components);
    }
}
