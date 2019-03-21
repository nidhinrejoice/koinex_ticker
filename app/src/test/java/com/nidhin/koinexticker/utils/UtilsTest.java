package com.nidhin.koinexticker.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void makeFirstLetterUpperCase() {
        String s = "hello world";
        assertEquals("Hello World", Utils.makeFirstLetterUpperCase(s));
        s = "helloworld";
        assertEquals("Helloworld", Utils.makeFirstLetterUpperCase(s));
    }

    @Test
    public void removeUnderScores() {
        String s = "hello_world";
        assertEquals("hello world", Utils.removeUnderScores(s));
    }

    @Test
    public void formatStringToDisplay() {
        String s = "hello_world";
        assertEquals("Hello World", Utils.formatStringToDisplay(s));
    }
}