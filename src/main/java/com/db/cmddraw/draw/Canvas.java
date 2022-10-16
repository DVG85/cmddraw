package com.db.cmddraw.draw;

import java.io.PrintStream;

public interface Canvas {
    void line(int x1, int y1, int x2, int y2);
    void rect(int x1, int y1, int x2, int y2);
    void draw(PrintStream printStream);

}
