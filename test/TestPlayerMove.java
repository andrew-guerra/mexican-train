package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import libs.game.PlayerMove;
import libs.game.Tile;

public class TestPlayerMove {
    @Test
    public void testConstructor() {
        Tile tile = new Tile(0, 0);
        int trainIndex = 0;
        int playerIndex = 0;
        PlayerMove playerMove = new PlayerMove(tile, trainIndex, playerIndex);

        assertEquals(tile, playerMove.tile);
        assertEquals(trainIndex, playerMove.trainIndex);
        assertEquals(playerIndex, playerMove.playerIndex);
    }

    @Test
    public void testToString() {
        Tile tile = new Tile(0, 0);
        int trainIndex = 0;
        int playerIndex = 0;
        PlayerMove playerMove = new PlayerMove(tile, trainIndex, playerIndex);

        assertEquals("tile: (0,0) train: 0 player: 0", playerMove.toString());
    }
}
