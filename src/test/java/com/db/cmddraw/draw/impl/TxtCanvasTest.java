package com.db.cmddraw.draw.impl;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TxtCanvasTest {


    @Test
    void negativeCanvasWidth() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TxtCanvas(-1, 10);
        });
        String expectedMessage = "Сanvas width cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void negativeCanvasHeight() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TxtCanvas(10, -1);
        });
        String expectedMessage = "Сanvas height cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void argumentsXOutOfBounds() {
        TxtCanvas canvas = new TxtCanvas(10, 10);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            canvas.line(1,1,11,10);
        });
        String expectedMessage = "X coordinate is out of bounds.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void argumentsYOutOfBounds() {
        TxtCanvas canvas = new TxtCanvas(10, 10);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            canvas.line(1,1,10,11);
        });
        String expectedMessage = "Y coordinate is out of bounds.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void line() {
        TxtCanvas canvas = new TxtCanvas(5, 5);
        canvas.line(1, 1, 1, 5);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final String cs = StandardCharsets.US_ASCII.name();
        String data = null;
        String expected = """
                -------
                |x    |
                |x    |
                |x    |
                |x    |
                |x    |
                -------
                """;
        try (PrintStream ps = new PrintStream(baos, true, cs)) {
            canvas.draw(ps);
            data = baos.toString(cs);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertEquals(expected, data);
    }

    @Test
    void rect() {
        TxtCanvas canvas = new TxtCanvas(5, 5);
        canvas.rect(1, 1, 5, 5);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final String cs = StandardCharsets.US_ASCII.name();
        String data = null;
        String expected = """
                -------
                |xxxxx|
                |x   x|
                |x   x|
                |x   x|
                |xxxxx|
                -------
                """;
        try (PrintStream ps = new PrintStream(baos, true, cs)) {
            canvas.draw(ps);
            data = baos.toString(cs);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertEquals(expected, data);
    }
}