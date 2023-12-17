package me.lucyydotp.scoreboard.paper;

import me.lucyydotp.scoreboard.api.board.Scoreboard;
import me.lucyydotp.scoreboard.api.component.ScoreboardComponent;
import me.lucyydotp.scoreboard.api.line.ScoreboardLine;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCommand extends Command {

    private static final ScoreboardComponent<Player> BOARD_ONE = ScoreboardComponent.fixed(
            Key.key("lucyydotp.test", "one"),
            List.of(
                    ScoreboardLine.line(Component.text("one"), Component.text(1)),
                    ScoreboardLine.line(Component.text("one")),
                    ScoreboardLine.line(Component.text("one"), Component.text(3))
            ),
            10
    );

    private static final ScoreboardComponent<Player> BOARD_TWO = ScoreboardComponent.fixed(
            Key.key("lucyydotp.test", "two"),
            List.of(
                    ScoreboardLine.line(Component.text("two"), Component.text(1)),
                    ScoreboardLine.line(Component.text("two")),
                    ScoreboardLine.line(Component.text("two"), Component.text(3))
            ),
            20
    );

    private static final ScoreboardComponent<Player> BOARD_THREE = ScoreboardComponent.fixed(
            Key.key("lucyydotp.test", "three"),
            List.of(
                    ScoreboardLine.line(Component.text("three"), Component.text(1)),
                    ScoreboardLine.line(Component.text("three")),
                    ScoreboardLine.line(Component.text("three"), Component.text(3))
            ),
            30
    );

    private static final List<ScoreboardComponent<Player>> BOARDS = List.of(BOARD_ONE, BOARD_TWO, BOARD_THREE);

    private final ScoreboardPaperPlugin plugin;

    public TestCommand(ScoreboardPaperPlugin plugin) {
        super("sbtest");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        final List<ScoreboardComponent<? super Player>> components = new ArrayList<>();
        for (final var arg : args) {
            components.add(BOARDS.get(Integer.parseInt(arg)));
        }
        plugin.manager.setScoreboard(player, Scoreboard.fixed(Component.text("Test board"), components));
        sender.sendMessage(components
                .stream()
                .map(it -> it.key().asString())
                .collect(Collectors.joining(", "))
        );

        plugin.manager.update();
        return true;
    }
}
