package com.db.cmddraw.cmd;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.db.cmddraw.cmd.CommandInfo.CommandKind.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class CommandInfoTest {

    @Test
    void parseEmptyString() {
        CommandInfo commandInfo = CommandInfo.parse(null);
        assertEquals(CommandInfo.UNKNOWN_COMMAND, commandInfo);
    }

    @Test
    void parseBlankString() {
        CommandInfo commandInfo = CommandInfo.parse("    ");
        assertEquals(CommandInfo.UNKNOWN_COMMAND, commandInfo);
    }

    @Test
    void parseUnknownCommandString() {
        CommandInfo commandInfo = CommandInfo.parse("D 1 2");
        assertEquals(CommandInfo.UNKNOWN_COMMAND, commandInfo);
    }

    @Test
    void parseCanvasCommand() {
        CommandInfo commandInfo = CommandInfo.parse("C 1 2");
        assertEquals(CANVAS, commandInfo.getKind());
        assertIterableEquals(List.of("1", "2"), commandInfo.getParams());
    }

    @Test
    void parseLineCommand() {
        CommandInfo commandInfo = CommandInfo.parse("L 1 2 5 2");
        assertEquals(LINE, commandInfo.getKind());
        assertIterableEquals(List.of("1", "2", "5", "2"), commandInfo.getParams());
    }

    @Test
    void parseRectangleCommand() {
        CommandInfo commandInfo = CommandInfo.parse("R 12 2 20 5");
        assertEquals(RECT, commandInfo.getKind());
        assertIterableEquals(List.of("12", "2", "20", "5"), commandInfo.getParams());
    }
}