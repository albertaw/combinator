package com.example.combinator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void compare_combo_equals_true() {
        Combo combo = new Combo();
        combo.addDigit(1);
        combo.addDigit(2);
        combo.addDigit(3);
        combo.addDigit(4);

        Combo combo2 = new Combo();
        combo2.addDigit(1);
        combo2.addDigit(2);
        combo2.addDigit(3);
        combo2.addDigit(4);

        assertTrue(combo.equals(combo2));
    }

    @Test
    public void compare_combo_equals_false() {
        Combo combo = new Combo();
        combo.addDigit(1);
        combo.addDigit(2);
        combo.addDigit(3);
        combo.addDigit(4);

        Combo combo2 = new Combo();
        combo2.addDigit(2);
        combo2.addDigit(2);
        combo2.addDigit(3);
        combo2.addDigit(4);

        assertFalse(combo.equals(combo2));
    }

    @Test
    public void combo_has_correct_number() {
        Combo combo = new Combo();
        combo.addDigit(1);
        combo.addDigit(2);
        combo.addDigit(3);
        combo.addDigit(4);

        Combo combo2 = new Combo();
        combo2.addDigit(2);
        combo2.addDigit(5);
        combo2.addDigit(6);
        combo2.addDigit(7);

        assertEquals(1, combo.compareTo(combo2));
    }

    @Test
    public void combo_has_correct_number_and_position() {
        Combo combo = new Combo();
        combo.addDigit(1);
        combo.addDigit(2);
        combo.addDigit(3);
        combo.addDigit(4);

        Combo combo2 = new Combo();
        combo2.addDigit(0);
        combo2.addDigit(2);
        combo2.addDigit(6);
        combo2.addDigit(7);

        assertEquals(0, combo.compareTo(combo2));
    }

    @Test
    public void win_game() {
        Game game = new Game();

        Combo combo = new Combo();
        combo.addDigit(1);
        combo.addDigit(2);
        combo.addDigit(3);
        combo.addDigit(4);

        game.setCurrentCombo(combo);

        Combo combo2 = new Combo();
        combo2.addDigit(1);
        combo2.addDigit(2);
        combo2.addDigit(3);
        combo2.addDigit(4);

        game.getPlayer().makeGuess(combo2);
        game.update();

        assertEquals("win", game.getState());
    }

    @Test
    public void loose_game() {
        Game game = new Game();
        game.setNumGuessesLeft(1);
        Combo combo = new Combo();
        combo.addDigit(1);
        combo.addDigit(2);
        combo.addDigit(3);
        combo.addDigit(4);

        game.setCurrentCombo(combo);

        Combo combo2 = new Combo();
        combo2.addDigit(0);
        combo2.addDigit(2);
        combo2.addDigit(3);
        combo2.addDigit(4);

        game.getPlayer().makeGuess(combo2);
        game.update();

        assertEquals("game over", game.getState());
    }
}