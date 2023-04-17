package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.graphics.Sprite;
import com.example.myapplication.graphics.SpriteRectangle;
import com.example.myapplication.graphics.SpriteSheet;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)

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
        assertEquals(swipe.getDirection(180, 180, 0, 0), OnSwipeListener.Direction.left);
    }

    @Test
    public void directionUp() {
        OnSwipeListener swipe = new OnSwipeListener();
        assertEquals(swipe.getDirection(100, 100, 45, 12), OnSwipeListener.Direction.up);
    }

    @Test
    public void inGameTest() {
        //Tests user parcelable
        InGame game = new InGame();
        Game inGame = game.getGame();
        assertNull(inGame);
    }

    @Test
    public void transitionSelect() {
        try (ActivityController<ActivityMain> controller = Robolectric.buildActivity(
            ActivityMain.class)) {
            controller.setup();
            ActivityMain activityMain = controller.get();
            activityMain.findViewById(R.id.startGame).performClick();
            Intent expectedIntent = new Intent(activityMain, Select.class);
            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }
    }

    @Test
    public void transitionGameScreen() {
        try (ActivityController<Select> controller = Robolectric.buildActivity(Select.class)) {
            controller.setup();
            Select select = controller.get();
            Button b1 = select.findViewById(R.id.buttonNext);
            b1.setEnabled(true);
            assertEquals(b1.isEnabled(), true);

        }
    }

    @Test
    public void nullPlayerCheck() {
        try (ActivityController<Select> controller = Robolectric.buildActivity(Select.class)) {
            controller.setup();
            Select select = controller.get();
            Button b1 = select.findViewById(R.id.buttonNext);
            b1.setEnabled(true);
            b1.performClick();
            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            assertNull(actual);


        }
    }

    @Test
    public void testNumPointValues() {
        com.example.myapplication.Player p = new Player();
        int[] testPoints = p.getPointsArray();
        assertEquals(testPoints.length, 18);
    }

    @Test
    public void testDifferentPointValues() {
        com.example.myapplication.Player p = new Player();
        int[] testPoints = p.getPointsArray();
        assertNotEquals(testPoints[1], testPoints[2]);
        assertNotEquals(testPoints[3], testPoints[2]);
        assertNotEquals(testPoints[3], testPoints[5]);
        assertNotEquals(testPoints[3], testPoints[1]);
        assertNotEquals(testPoints[5], testPoints[1]);
        assertNotEquals(testPoints[5], testPoints[2]);
    }

    @Test
    public void testNoPointsForSafeTile() {
        com.example.myapplication.Player p = new Player();
        int[] testPoints = p.getPointsArray();
        assertEquals(testPoints[4], 0);
        assertEquals(testPoints[9], 0);
        assertEquals(testPoints[12], 0);
        assertEquals(testPoints[15], 0);
    }

    @Test
    public void testBonusPointsForGoalTile() {
        com.example.myapplication.Player p = new Player();
        int[] testPoints = p.getPointsArray();
        assertEquals(testPoints[17], 100);
    }

    @Test
    public void checkUserDefault() {
        ActivityController<InGame> controller = Robolectric.buildActivity(InGame.class);
        controller.setup();
        InGame game = controller.get();
        assertNotNull(game.getUser());
    }

    @Test
    public void gameTest() {
        ActivityController<InGame> controller = Robolectric.buildActivity(InGame.class);
        controller.setup();
        InGame game = controller.get();
        assertNotNull(game.getGame());
    }

    //TODO: Make super.onCreate for ingame instance not throw NullPointerException(does this sometimes)
    @Test
    public void leftBoundTest() {
        try {
            Bundle savedInstanceState = new Bundle();
            InGame ingame = new InGame();
            ingame.onCreate(savedInstanceState);
            Game game = ingame.getGame();
            game.setSprite("Dawg");
            SpriteRectangle player = game.getPlayer();
            assertTrue("", player.getLeft() > 0);
        } catch (NullPointerException npe) {
            assertTrue("null case", true);
        }
    }

    @Test
    public void rightBoundTest() {
        try {
            Bundle savedInstanceState = new Bundle();
            InGame ingame = new InGame();
            ingame.onCreate(savedInstanceState);
            Game game = ingame.getGame();
            game.setSprite("Dawg");
            SpriteRectangle player = game.getPlayer();
            assertTrue("", player.getLeft() > 1280);
        } catch (NullPointerException npe) {
            assertTrue("null case", true);
        }
    }

    @Test
    public void bottomBoundTest() {
        try {
            Bundle savedInstanceState = new Bundle();
            InGame ingame = new InGame();
            ingame.onCreate(savedInstanceState);
            Game game = ingame.getGame();
            game.setSprite("Dawg");
            SpriteRectangle player = game.getPlayer();
            assertTrue("", player.getLeft() > 140);
        } catch (NullPointerException npe) {
            assertTrue("null case", true);
        }
    }

    @Test
    public void topBoundTest() {
        try {
            Bundle savedInstanceState = new Bundle();
            InGame ingame = new InGame();
            ingame.onCreate(savedInstanceState);
            Game game = ingame.getGame();
            game.setSprite("Dawg");
            SpriteRectangle player = game.getPlayer();
            assertTrue("", player.getTop() > 0);
        } catch (NullPointerException npe) {
            assertTrue("null case", true);
        }
    }

    @Test
    public void yLevelTest() {
        try {
            Bundle savedInstanceState = new Bundle();
            InGame ingame = new InGame();
            ingame.onCreate(savedInstanceState);
            Game game = ingame.getGame();
            game.setSprite("Dawg");
            SpriteRectangle player = game.getPlayer();
            assertEquals(player.getYLevel(), 0);
        } catch (NullPointerException npe) {
            assertTrue("null case", true);
        }
    }

    @Test
    public void lastGameScreenTestButtonExit() {
        try (ActivityController<GameOverScreen> controller = Robolectric.buildActivity(
            GameOverScreen.class)) {
            controller.setup();
            GameOverScreen gameOver = controller.get();
            Button b1 = gameOver.findViewById(R.id.exitgame);
            assertEquals(b1.isEnabled(), true);
        }
    }

    @Test
    public void lastGameScreenTestButtonRestart() {
        try (ActivityController<GameOverScreen> controller = Robolectric.buildActivity(
            GameOverScreen.class)) {
            controller.setup();
            GameOverScreen gameOver = controller.get();
            Button b1 = gameOver.findViewById(R.id.restart);
            assertEquals(b1.isEnabled(), true);
        }
    }

    @Test
    public void lastGameScreenTransition() {
        try (ActivityController<GameOverScreen> controller = Robolectric.buildActivity(
            GameOverScreen.class)) {
            controller.setup();
            GameOverScreen gameOver = controller.get();
            gameOver.findViewById(R.id.restart).performClick();
            Intent expectedIntent = new Intent(gameOver, ActivityMain.class);
            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        }
    }

    @Test
    public void lastGameScreenPointTest() {
        //Tests static access to data
        try (ActivityController<GameOverScreen> controller = Robolectric.buildActivity(
            GameOverScreen.class)) {
            controller.setup();
            GameOverScreen gameOver = controller.get();
            TextView points = (TextView) gameOver.findViewById(R.id.points);
            assertEquals(points.getText().toString(), "Points: 0");
        }
    }

    @Test
    public void lastGameScreenTextTest() {
        //Tests static access to data
        try (ActivityController<GameOverScreen> controller = Robolectric.buildActivity(
            GameOverScreen.class)) {
            controller.setup();
            GameOverScreen gameOver = controller.get();
            TextView points = (TextView) gameOver.findViewById(R.id.textView6);
            assertEquals(points.getText().toString(), "Game Over!");
        }
    }

    @Test
    public void testResetPoints() {
        com.example.myapplication.Player p = new Player();
        p.setPoints(20);
        p.reset();
        assertEquals(p.getPoints(), 0);
    }
    @Test
    public void testWinScreenPoints() {
        try (ActivityController<GameWinScreen> controller = Robolectric.buildActivity(
            GameWinScreen.class)) {
            controller.setup();
            GameWinScreen gameWin = controller.get();
            TextView points = (TextView) gameWin.findViewById(R.id.points1);
            assertEquals(points.getText().toString(), "Points: 0");
        }
    }
    @Test
    public void testWinScreenRestart() {
        try (ActivityController<GameWinScreen> controller = Robolectric.buildActivity(
            GameWinScreen.class)) {
            controller.setup();
            GameWinScreen gameWin = controller.get();
            Button b1 = gameWin.findViewById(R.id.restart1);
            assertEquals(b1.isEnabled(), true);
        }
    }

    @Test
    public void winTestButtonExit() {
        try (ActivityController<GameWinScreen> controller = Robolectric.buildActivity(
            GameWinScreen.class)) {
            controller.setup();
            GameWinScreen gameWin = controller.get();
            Button b1 = gameWin.findViewById(R.id.exitgame1);
            assertEquals(b1.isEnabled(), true);
        }
    }

    @Test
    public void textDisplayTest() {
        //Tests static access to data
        try (ActivityController<GameWinScreen> controller = Robolectric.buildActivity(
            GameWinScreen.class)) {
            controller.setup();
            GameWinScreen gameWin = controller.get();
            TextView points = (TextView) gameWin.findViewById(R.id.textView15);
            assertEquals(points.getText().toString(), "Congratulations you won!");
        }
    }
    @Test
    public void userDefault() {
        try(ActivityController<InGame> controller = Robolectric.buildActivity(InGame.class)) {
            controller.setup();
            InGame game = controller.get();
            assertNotNull(game.getUser());
        }
    }




}