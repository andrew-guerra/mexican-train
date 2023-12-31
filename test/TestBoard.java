package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import libs.game.Board;
import libs.game.PlayerMove;
import libs.game.Tile;
import libs.game.Train;

public class TestBoard {
    @Test
    public void testConstructor() {
        Board board = constructEmptyBoard(5, 0);

        assertEquals("[][][][][]", board.toString());
    }

    @Test
    public void testGenerateMoves() {
        Board board = constructEmptyBoard(5, 0);
        PlayerMove[] legalMoves = new PlayerMove[]{new PlayerMove(new Tile(0, 0), 0, 0)};

        assertArrayEquals(legalMoves, board.generateMoves(new Tile(0, 0), 0));
    }

    @Test
    public void testIsPlayibleByPlayer() {
        Board board = constructEmptyBoard(5, 0);
        PlayerMove playibleMove = new PlayerMove(new Tile(0, 0), 0, 0);
        PlayerMove nonplayibleMove = new PlayerMove(new Tile(0, 0), 1, 0);

        assertTrue(board.isPlayibleByPlayer(playibleMove));
        assertFalse(board.isPlayibleByPlayer(nonplayibleMove));
    }

    @Test
    public void testIsLegalMove() {
        Board board = constructEmptyBoard(5, 0);
        PlayerMove legalMove = new PlayerMove(new Tile(0, 0), 0, 0);
        PlayerMove illegalMove = new PlayerMove(new Tile(0, 0), 0, 1);

        assertTrue(board.isLegalMove(legalMove));
        assertFalse(board.isLegalMove(illegalMove));
    }

    @Test
    public void testMakeMove() {
        Board board = constructEmptyBoard(5, 0);

        board.makeMove(new PlayerMove(new Tile(0, 0), 0, 0));
        
        assertEquals("[(0,0)][][][][]", board.toString());

        board.makeMove(new PlayerMove(new Tile(1, 0), 1, 1));
        
        assertEquals("[(0,0)][(0,1)][][][]", board.toString());

        board.makeMove(new PlayerMove(new Tile(1, 1), 2, 2));
        
        assertEquals("[(0,0)][(0,1)][][][]", board.toString());
    }

    @Test
    public void testIsPublic() {
        Board board = constructEmptyBoard(5, 0);

        for (int i = 0; i < 5; i++) {
            assertFalse(board.isPublic(i));
        }

        board.setIsPublic(0, true);

        assertTrue(board.isPublic(0));
    }

    @Test
    public void tetSetIsPublic() {
        Board board = constructEmptyBoard(5, 0);

        board.setIsPublic(0, true);

        assertTrue(board.isPublic(0));

        board.setIsPublic(0, false);

        assertFalse(board.isPublic(0));
    }

    @Test
    public void testToString() {
        Board board = constructEmptyBoard(5, 0);

        assertEquals("[][][][][]", board.toString());

        board.makeMove(new PlayerMove(new Tile(0, 0), 0, 0));

        assertEquals("[(0,0)][][][][]", board.toString());
    }

    @Test
    public void testEquals() {
        Board board = constructEmptyBoard(5, 0);

        assertEquals(board, board);
    }

    private Board constructEmptyBoard(int num, int startingValue) {
        Train[] trains = new Train[num];

        for(int i = 0; i < trains.length; i++) {
            trains[i] = new Train(startingValue, i);
        }

        return new Board(trains);
    }
}
