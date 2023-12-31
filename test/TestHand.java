package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import libs.game.Hand;
import libs.game.Tile;

public class TestHand {
    @Test
    public void testArrayConstructor() {
        Hand emptyHand = new Hand(new Tile[]{new Tile(1,1)});
        
        assertFalse(emptyHand.isEmpty());
        assertEquals(2, emptyHand.getScore());
        assertEquals("(1,1)", emptyHand.toString());
    }

    @Test
    public void testEmptyConstructor() {
        Hand emptyHand = new Hand();
        
        assertTrue(emptyHand.isEmpty());
        assertEquals(0, emptyHand.getScore());
        assertEquals("", emptyHand.toString());
    }

    @Test
    public void testAdd() {
        Hand hand = new Hand();
        hand.add(new Tile(1,2));

        assertFalse(hand.isEmpty());
        assertEquals(3, hand.getScore());
    }

    @Test
    public void testRemove() {
        Hand hand = new Hand(new Tile[]{new Tile(1, 1)});

        hand.remove(new Tile(1, 1));

        assertTrue(hand.isEmpty());
    }

    @Test
    public void testGetTiles() {
        
    }

    @Test
    public void testIsEmpty() {
        Hand emptyHand = new Hand();

        assertTrue(emptyHand.isEmpty());

        Hand hand = new Hand(new Tile[]{new Tile(1,1)});

        assertFalse(hand.isEmpty());
    }

    @Test
    public void testGetScore() {
        Hand hand = new Hand();

        assertEquals(0, hand.getScore());

        hand.add(new Tile(1, 1));

        assertEquals(2, hand.getScore());
    }

    @Test
    public void testToString() {
        Hand emptyHand = new Hand();
        assertEquals("", emptyHand.toString());

        emptyHand.add(new Tile(0, 1));
        emptyHand.add(new Tile(1, 2));

        assertEquals("(0,1)(1,2)", emptyHand.toString());
    }
}
