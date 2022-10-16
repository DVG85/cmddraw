package com.db.cmddraw.draw.impl;


import com.db.cmddraw.draw.Canvas;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;
import java.util.Arrays;


public class TxtCanvas implements Canvas {

    private static final char EMPTY_CHAR = ' ';
    private static final char POINT_CHAR = 'x';
    private static final char BORDER_X_CHAR = '-';
    private static final char BORDER_Y_CHAR = '|';

    private final int width;
    private final int height;
    private final char[][] points;

    public TxtCanvas(int width, int height) {
        if (width < 0)
            throw new IllegalArgumentException("Сanvas width cannot be negative.");
        if (height < 0)
            throw new IllegalArgumentException("Сanvas height cannot be negative.");

        this.width = width;
        this.height = height;
        points = new char[height][width];
        init();
    }

    private void init() {
        for (char[] point : points) {
            Arrays.fill(point, EMPTY_CHAR);
        }
    }

    private void checkX(int x) {
        if (x <= 0 || x > width)
            throw new IllegalArgumentException("X coordinate is out of bounds.");
    }

    private void checkY(int y) {
        if (y <= 0 || y > height)
            throw new IllegalArgumentException("Y coordinate is out of bounds.");
    }

    @Override
    public void line(int x1, int y1, int x2, int y2) {
        checkX(x1);
        checkX(x2);
        checkY(y1);
        checkY(y2);

        if (x1 == x2) {
            for (int i = y1 - 1; i < y2; i++) {
                points[i][x1 - 1] = POINT_CHAR;
            }
        } else {
            for (int i = x1 - 1; i < x2; i++) {
                points[y1 - 1][i] = POINT_CHAR;
            }
        }
    }

    @Override
    public void rect(int x1, int y1, int x2, int y2) {
        line(x1, y1, x1, y2);
        line(x1, y1, x2, y1);
        line(x2, y1, x2, y2);
        line(x1, y2, x2, y2);
    }

    @Override
    public void draw(PrintStream printStream) {
        StringBuilder sb = new StringBuilder();

        sb.append(StringUtils.repeat(BORDER_X_CHAR, width + 2));
        sb.append('\n');

        for (char[] point : points) {
            sb.append(BORDER_Y_CHAR);
            for (char c : point) {
                sb.append(c);
            }
            sb.append(BORDER_Y_CHAR);
            sb.append('\n');
        }

        sb.append(StringUtils.repeat(BORDER_X_CHAR, width + 2));

        printStream.println(sb);
    }

}
