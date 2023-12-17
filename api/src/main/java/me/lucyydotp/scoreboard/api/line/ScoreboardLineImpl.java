package me.lucyydotp.scoreboard.api.line;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

record ScoreboardLineImpl(
        @Override Component name,
        @Nullable @Override Component score
) implements ScoreboardLine {
}
