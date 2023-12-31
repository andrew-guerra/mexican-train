package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import libs.game.Tile;

public class TestTile {
    @Test
    public void testConstructor() {
        Tile blankTile = new Tile(0, 0);

        assertTrue(blankTile.isCompatible(0));
        assertFalse(blankTile.isCompatible(1));

        Tile variedTile = new Tile(1,2);

        assertTrue(variedTile.hasValue(1));
        assertTrue(variedTile.hasValue(2));
        assertTrue(variedTile.isCompatible(1));
        assertFalse(variedTile.isCompatible(blankTile));
        assertTrue(variedTile.isCompatible(variedTile));
    }

    @Test
    public void testIsCompatibleValue() {
        Tile variedTile = new Tile(1, 2);

        assertTrue(variedTile.isCompatible(1));
        assertTrue(variedTile.isCompatible(2));
    }

    @Test
    public void testIsCompatibleTile() {
        Tile tile1 = new Tile(1, 2);
        Tile tile2 = new Tile(3, 4);
        Tile tile3 = new Tile(2, 1);
        Tile tile4 = new Tile(3, 5);
        Tile tile5 = new Tile(4, 5);

        assertTrue(tile1.isCompatible(tile1));
        assertTrue(tile1.isCompatible(tile3));
        assertFalse(tile1.isCompatible(tile2));

        assertFalse(tile2.isCompatible(tile4));
        assertTrue(tile5.isCompatible(tile2));
    }

    @Test
    public void testHasValue() {
        Tile variedTile = new Tile(1, 2);

        assertTrue(variedTile.hasValue(1));
        assertTrue(variedTile.hasValue(2));
    }

    @Test
    public void testIsDouble() {
        Tile doubleTile = new Tile(1, 1);
        assertTrue(doubleTile.isDouble());

        Tile nonDoubleTile = new Tile(1, 2);
        assertFalse(nonDoubleTile.isDouble());
    }

    @Test
    public void testGetScore() {
        Tile doubleZero = new Tile(0, 0);
        assertEquals(50, doubleZero.getScore());

        Tile tile = new Tile(1, 2);
        assertEquals(3, tile.getScore());
    }

    @Test
    public void testOrientValue() {
        Tile tile = new Tile(1, 2);

        assertEquals("(1,2)", tile.toString());

        tile.orient(2);

        assertEquals("(2,1)", tile.toString());

        tile.orient(1);

        assertEquals("(1,2)", tile.toString());
    }

    @Test
    public void testOrientTile() {
        Tile tile1 = new Tile(1, 2);
        Tile tile2 = new Tile(2, 1);

        tile2.orient(tile1);

        assertEquals("(2,1)", tile2.toString());

        tile1.orient(tile1);

        assertEquals("(2,1)", tile1.toString());
    }

    @Test
    public void testFilpOrientation() {
        Tile tile = new Tile(1,2);

        tile.flipOrientation();

        assertEquals("(2,1)", tile.toString());
    }

    @Test
    public void testEquals() {
        Tile tile = new Tile(0, 1);

        assertEquals(tile, tile);

        Tile unflippedTile = new Tile(0, 1);
        tile.flipOrientation();

        assertEquals(unflippedTile, tile);

        Tile unequalTile = new Tile(3, 2);
        Tile unequalDouble = new Tile(0, 0);

        assertNotEquals(unequalTile, tile);
        assertNotEquals(unequalDouble, tile);
    }
    
    @Test
    public void testToString() {
        Tile tile = new Tile(1,2);
        assertEquals("(1,2)", tile.toString());
    }
}