package com.db.cmddraw.cmd.impl;

import com.db.cmddraw.cmd.Command;
import com.db.cmddraw.cmd.CommandInfo;
import com.db.cmddraw.draw.Canvas;
import com.db.cmddraw.draw.impl.TxtCanvas;

public class CanvasCommand implements Command {
    @Override
    public boolean support(CommandInfo commandInfo) {
        return CommandInfo.CommandKind.CANVAS.equals(commandInfo.getKind());
    }

    @Override
    public Canvas apply(Canvas canvas, CommandInfo commandInfo) {
        checkCountArguments(commandInfo, 2);
        int width = Integer.parseInt(commandInfo.getParams().get(0));
        int height = Integer.parseInt(commandInfo.getParams().get(1));
        return new TxtCanvas(width, height);
    }


}
