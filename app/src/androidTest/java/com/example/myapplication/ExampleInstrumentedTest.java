package com.example.myapplication;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void testEasyLives(){
        Player p = new Player();
        p.setDifficulty(1);
        assertEquals(15, p.getLives());
    }

    @Test
    public void testMediumLives(){
        Player p = new Player();
        p.setDifficulty(2);
        assertEquals(10, p.getLives());
    }

    @Test
    public void testHardLives(){
        Player p = new Player();
        p.setDifficulty(3);
        assertEquals(5, p.getLives());
    }

    @Test
    public void nullName(){
        Player p = new Player();
        assertEquals(false, p.nameValid(null));
    }

    @Test
    public void blankName(){
        Player p = new Player();
        assertEquals(false, p.nameValid(""));
    }

    @Test
    public void whitespaceName(){
        Player p = new Player();
        assertEquals(false, p.nameValid("    "));
    }

    @Test
    public void validName(){
        Player p = new Player();
        assertEquals(true, p.nameValid("Bob"));
    }

}