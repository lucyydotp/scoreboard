package me.lucyydotp.scoreboard.api.board;

import me.lucyydotp.scoreboard.api.component.ScoreboardComponent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;

import java.util.List;

record FixedBoard<P extends Audience & Identified>(Component title,
                                                   List<ScoreboardComponent<? super P>> components) implements Scoreboard<P> {
    @Override
    public boolean add(ScoreboardComponent<? super P> component) {
        throw new IllegalStateException("Can't add a component to a fixed board");
    }

    @Override
    public boolean remove(Key key) {
        throw new IllegalStateException("Can't add a component to a fixed board");
    }

    @Override
    public void title(Component component) {
        throw new IllegalStateException("Can't set a fixed board's title");
    }
}
