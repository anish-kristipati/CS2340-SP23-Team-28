package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.graphics.Rectangle;
import com.example.myapplication.graphics.SpriteSheet;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Mock
    Context mockContext;

    @Test
    public void testEasyLives() {
        com.example.myapplication.Player p = new com.example.myapplication.Player();
        p.setDifficulty(1);
        assertEquals(15, p.getLives());
    }

    @Test
    public void testMediumLives() {
        com.example.myapplication.Player p = new com.example.myapplication.Player();
        p.setDifficulty(2);
        assertEquals(10, p.getLives());
    }

    @Test
    public void testHardLives() {
        com.example.myapplication.Player p = new com.example.myapplication.Player();
        p.setDifficulty(3);
        assertEquals(5, p.getLives());
    }

    @Test
    public void nullName() {
        com.example.myapplication.Player p = new com.example.myapplication.Player();
        assertEquals(false, p.nameValid(null));
    }

    @Test
    public void blankName() {
        com.example.myapplication.Player p = new com.example.myapplication.Player();
        assertEquals(false, p.nameValid(""));
    }

    @Test
    public void whitespaceName() {
        com.example.myapplication.Player p = new com.example.myapplication.Player();
        assertEquals(false, p.nameValid("    "));
    }

    @Test
    public void validName() {
        com.example.myapplication.Player p = new Player();
        assertEquals(true, p.nameValid("Bob"));
    }

    @Test
    public void directionDown() {
        OnSwipeListener swipe = new OnSwipeListener();
        assertEquals(swipe.getDirection(12, 12, 40, 45), OnSwipeListener.Direction.down);
    }

    @Test
    public void directionLeft() {
        OnSwipeListener swipe = new OnSwipeListener();
        assertEquals(swipe.getDirection(45, 45, 0, 0), OnSwipeListener.Direction.left);
    }

    @Test
    public void directionUp() {
        OnSwipeListener swipe = new OnSwipeListener();
        assertEquals(swipe.getDirection(45, 45, 45, 0), OnSwipeListener.Direction.up);
    }


}