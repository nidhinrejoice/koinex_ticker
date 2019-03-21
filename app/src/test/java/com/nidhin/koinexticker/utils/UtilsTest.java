package com.nidhin.koinexticker.utils;

import com.nidhin.koinexticker.homescreen.data.Coin;

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
    @Test
    public void testSpread() {
        Coin coin=new Coin();
        coin.setLastTradedPrice("200.0");
        coin.setLowestAsk("200.0");
        coin.setHighestBid("189.0");
        assertEquals("5.5", Utils.calculateSpread(coin));
    }
}