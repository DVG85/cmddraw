package com.db.cmddraw.cmd;

import com.db.cmddraw.draw.Canvas;

public interface Command {
    boolean support(CommandInfo commandInfo);
    Canvas apply(Canvas canvas, CommandInfo commandInfo);

    default void checkCountArguments(CommandInfo commandInfo, int cnt) {
        if (commandInfo.getParams().size() < cnt)
            throw new IllegalArgumentException("Incorrect count of command arguments.");
    }
}
