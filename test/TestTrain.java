package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import libs.game.Tile;
import libs.game.Train;

public class TestTrain {
    @Test
    public void testConstructor() {
        Train train = new Train(0, 0);

        assertTrue(train.isEmpty());
        assertTrue(train.isPlayible(new Tile(0,0)));
        assertFalse(train.isPublic());
        assertEquals(0, train.getTrainIndex());
    }

    @Test
    public void testIsPlayible() {
        Train train = new Train(0, 0);

        assertFalse(train.isPlayible(null));
        assertFalse(train.isPlayible(new Tile(1,2)));
        assertTrue(train.isPlayible(new Tile(0,0)));
        assertTrue(train.isPlayible(new Tile(1,0)));

        train.play(new Tile(1,0));

        assertFalse(train.isPlayible(new Tile(0,0)));
        assertTrue(train.isPlayible(new Tile(1,2)));
    }

    @Test
    public void testIsMergeable() {
        Train train = new Train(0, 0);

        assertFalse(train.isMergeable(null));

        Train mergeableTrain = new Train(0, 0);

        assertTrue(train.isMergeable(mergeableTrain));

        mergeableTrain.play(new Tile(0, 1));

        assertTrue(train.isMergeable(mergeableTrain));
    }

    @Test
    public void testPlayible() {
        Train train = new Train(0, 0);

        Tile[] tiles = new Tile[]{new Tile(0, 1), new Tile(1, 2)};
        Tile[] playibleTiles = new Tile[]{new Tile(0, 1)};

        assertArrayEquals(playibleTiles, train.playible(tiles));
    }

    @Test
    public void testMergeable() {
        Train train  = new Train(0, 0);

        Train mergeableTrain = new Train(0, 0);
        mergeableTrain.play(new Tile(0, 0));

        Train nonMergeableTrain = new Train(1, 0);
        nonMergeableTrain.play(new Tile(1, 0));

        Train[] trainCandidates = new Train[]{mergeableTrain, nonMergeableTrain};
        Train[] mergeableTrains = new Train[]{mergeableTrain};

        assertArrayEquals(mergeableTrains, train.mergeable(trainCandidates));
    }

    @Test
    public void testPlay() {
        Train train = new Train(0, 0);

        train.play(null);
        
        assertEquals("", train.toString());
        
        train.play(new Tile(1, 1));

        assertEquals("", train.toString());

        train.play(new Tile(1, 0));

        assertEquals("(0,1)", train.toString());

        train.play(new Tile(1, 2));

        assertEquals("(0,1)(1,2)", train.toString());
    }

    @Test
    public void testMerge() {
        Train train = new Train(0, 0);

        train.merge(train);

        assertTrue(train.isEmpty());

        Train mergeableTrain = new Train(0, 0);
        mergeableTrain.play(new Tile(0, 1));
        mergeableTrain.play(new Tile(1, 2));

        train.merge(mergeableTrain);

        assertEquals("(0,1)(1,2)", train.toString());
    }

    @Test
    public void testIsEmpty() {
        Train train = new Train(0, 0);

        assertTrue(train.isEmpty());

        train.play(new Tile(0, 0));

        assertFalse(train.isEmpty());
    }

    @Test
    public void testGetTrainIndex() {
        Train train = new Train(0, 1);

        assertEquals(1, train.getTrainIndex());
    }

    @Test
    public void testIsPublic() {
        Train train = new Train(0, 0);

        assertFalse(train.isPublic());

        train.setIsPublic(true);

        assertTrue(train.isPublic());
    }

    @Test
    public void testToString() {
        Train train = new Train(0, 0);

        assertEquals("", train.toString());

        train.play(new Tile(0,0));

        assertEquals("(0,0)", train.toString());
    }
}
