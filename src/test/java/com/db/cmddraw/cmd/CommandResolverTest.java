package com.db.cmddraw.cmd;

import com.db.cmddraw.cmd.impl.CanvasCommand;
import com.db.cmddraw.cmd.impl.LineCommand;
import com.db.cmddraw.cmd.impl.RectCommand;
import com.db.cmddraw.cmd.impl.UnknownCommand;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommandResolverTest {

    @Test
    void resolveCanvasCommand() {
        CommandResolver commandResolver = new CommandResolver(Set.of(
                new CanvasCommand(),
                new LineCommand(),
                new RectCommand(),
                new UnknownCommand()
        ));

        Command result = commandResolver.resolve(new CommandInfo(CommandInfo.CommandKind.CANVAS, null));
        assertInstanceOf(CanvasCommand.class, result);
    }

    @Test
    void resolveRectCommand() {
        CommandResolver commandResolver = new CommandResolver(Set.of(
                new CanvasCommand(),
                new LineCommand(),
                new RectCommand(),
                new UnknownCommand()
        ));

        Command result = commandResolver.resolve(new CommandInfo(CommandInfo.CommandKind.RECT, null));
        assertInstanceOf(RectCommand.class, result);
    }

    @Test
    void resolveLineCommand() {
        CommandResolver commandResolver = new CommandResolver(Set.of(
                new CanvasCommand(),
                new LineCommand(),
                new RectCommand(),
                new UnknownCommand()
        ));

        Command result = commandResolver.resolve(new CommandInfo(CommandInfo.CommandKind.LINE, null));
        assertInstanceOf(LineCommand.class, result);
    }

    @Test
    void resolveUnknownCommand() {
        CommandResolver commandResolver = new CommandResolver(Set.of(
                new CanvasCommand(),
                new LineCommand(),
                new RectCommand(),
                new UnknownCommand()
        ));

        Command result = commandResolver.resolve(new CommandInfo(CommandInfo.CommandKind.UNKNOWN, null));
        assertInstanceOf(UnknownCommand.class, result);
    }
}