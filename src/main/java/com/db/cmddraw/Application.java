package com.db.cmddraw;

import com.db.cmddraw.cmd.CmdDrawer;
import com.db.cmddraw.cmd.CommandResolver;
import com.db.cmddraw.cmd.impl.CanvasCommand;
import com.db.cmddraw.cmd.impl.LineCommand;
import com.db.cmddraw.cmd.impl.RectCommand;
import com.db.cmddraw.cmd.impl.UnknownCommand;

import java.util.Set;

public class Application {

    public static void main(String[] args) {
        CommandResolver commandResolver = new CommandResolver(Set.of(
                new CanvasCommand(),
                new LineCommand(),
                new RectCommand(),
                new UnknownCommand()
        ));
        CmdDrawer cmdDrawer = new CmdDrawer(commandResolver, System.in, System.out);
        cmdDrawer.draw();
    }


}
