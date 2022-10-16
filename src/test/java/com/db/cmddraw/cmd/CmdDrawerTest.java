package com.db.cmddraw.cmd;

import com.db.cmddraw.cmd.impl.CanvasCommand;
import com.db.cmddraw.cmd.impl.LineCommand;
import com.db.cmddraw.cmd.impl.RectCommand;
import com.db.cmddraw.cmd.impl.UnknownCommand;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class CmdDrawerTest {

    private final CommandResolver commandResolver = new CommandResolver(Set.of(
            new CanvasCommand(),
            new LineCommand(),
            new RectCommand(),
            new UnknownCommand()
    ));

    @Test
    void drawLine() {
        String cmd = "C 5 5\r\nL 1 1 1 5\r\nQ";
        try (InputStream inputStream = new ByteArrayInputStream(cmd.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)) {
            CmdDrawer cmdDrawer = new CmdDrawer(commandResolver, inputStream, printStream);
            cmdDrawer.draw();
            String result = out.toString().replace("enter command: ", "");
            String expected = """
                    -------
                    |     |
                    |     |
                    |     |
                    |     |
                    |     |
                    -------
                    -------
                    |x    |
                    |x    |
                    |x    |
                    |x    |
                    |x    |
                    -------
                    """;
            assertEquals(expected, result);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void drawRectangle() {
        String cmd = "C 5 5\r\nR 1 1 5 5\r\nQ";
        try (InputStream inputStream = new ByteArrayInputStream(cmd.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)) {
            CmdDrawer cmdDrawer = new CmdDrawer(commandResolver, inputStream, printStream);
            cmdDrawer.draw();
            String result = out.toString().replace("enter command: ", "");
            String expected = """
                    -------
                    |     |
                    |     |
                    |     |
                    |     |
                    |     |
                    -------
                    -------
                    |xxxxx|
                    |x   x|
                    |x   x|
                    |x   x|
                    |xxxxx|
                    -------
                    """;
            assertEquals(expected, result);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void drawQuit() {
        String cmd = "C 5 5\r\nQ";
        try (InputStream inputStream = new ByteArrayInputStream(cmd.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)) {
            CmdDrawer cmdDrawer = new CmdDrawer(commandResolver, inputStream, printStream);
            cmdDrawer.draw();
            String result = out.toString().replace("enter command: ", "");
            String expected = """
                    -------
                    |     |
                    |     |
                    |     |
                    |     |
                    |     |
                    -------
                    """;
            assertEquals(expected, result);
        } catch (IOException e) {
            fail();
        }
    }
}