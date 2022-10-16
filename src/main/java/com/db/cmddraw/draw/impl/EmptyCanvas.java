package com.db.cmddraw.draw.impl;

import com.db.cmddraw.draw.Canvas;

import java.io.PrintStream;

/**
 * EmptyCanvas implements Canvas and does nothing.
 */
public class EmptyCanvas implements Canvas {
    @Override
    public void line(int x1, int y1, int x2, int y2) {
        // Do nothing.
    }

    @Override
    public void rect(int x1, int y1, int x2, int y2) {
        // Do nothing.
    }

    @Override
    public void draw(PrintStream printStream) {
        // Do nothing.
    }
}
