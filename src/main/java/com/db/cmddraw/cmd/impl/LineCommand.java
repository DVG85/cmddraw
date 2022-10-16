package com.db.cmddraw.cmd.impl;

import com.db.cmddraw.cmd.Command;
import com.db.cmddraw.cmd.CommandInfo;
import com.db.cmddraw.draw.Canvas;

public class LineCommand implements Command {
    @Override
    public boolean support(CommandInfo commandInfo) {
        return CommandInfo.CommandKind.LINE.equals(commandInfo.getKind());
    }

    @Override
    public Canvas apply(Canvas canvas, CommandInfo commandInfo) {
        checkCountArguments(commandInfo, 4);
        int x1 = Integer.parseInt(commandInfo.getParams().get(0));
        int y1 = Integer.parseInt(commandInfo.getParams().get(1));
        int x2 = Integer.parseInt(commandInfo.getParams().get(2));
        int y2 = Integer.parseInt(commandInfo.getParams().get(3));
        canvas.line(x1, y1, x2, y2);
        return canvas;
    }
}
