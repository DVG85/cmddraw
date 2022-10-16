package com.db.cmddraw.cmd.impl;

import com.db.cmddraw.cmd.Command;
import com.db.cmddraw.cmd.CommandInfo;
import com.db.cmddraw.draw.Canvas;

public class UnknownCommand implements Command {

    @Override
    public boolean support(CommandInfo commandInfo) {
        return false;
    }

    @Override
    public Canvas apply(Canvas canvas, CommandInfo commandInfo) {
        throw new UnsupportedOperationException("Incorrect command.");
    }
}
