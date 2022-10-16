package com.db.cmddraw.cmd;

import com.db.cmddraw.draw.Canvas;
import com.db.cmddraw.draw.impl.EmptyCanvas;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static com.db.cmddraw.cmd.CommandInfo.CommandKind.QUIT;

public class CmdDrawer {

    private Canvas canvas = new EmptyCanvas();
    private final CommandResolver commandResolver;
    private final Scanner in;
    private final PrintStream out;

    public CmdDrawer(CommandResolver commandResolver, InputStream inputStream, PrintStream outputStream) {
        this.commandResolver = commandResolver;
        this.in = new Scanner(inputStream);
        out = outputStream;
    }

    private CommandInfo nextCmd() {
        out.print("enter command: ");
        String cmd = in.nextLine();
        return CommandInfo.parse(cmd);
    }

    public void draw() {
        CommandInfo commandInfo;
        while ((commandInfo = nextCmd()).getKind() != QUIT) {
            try {
                Command command = commandResolver.resolve(commandInfo);
                canvas = command.apply(canvas, commandInfo);
                canvas.draw(out);
            } catch (NumberFormatException ex) {
                out.println("Incorrect command argument.");
            } catch (UnsupportedOperationException | IllegalArgumentException ex) {
                out.println(ex.getMessage());
            }
        }
    }


}
