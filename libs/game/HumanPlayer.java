package libs.game;

import java.util.ArrayList;
import java.util.Random;

public class HumanPlayer extends Player {
    public HumanPlayer(Hand hand, int playerIndex, int trainIndex) {
        super(hand, playerIndex, trainIndex);
    }
    
    public HumanPlayer(int playerIndex, int trainIndex) {
        super(playerIndex, trainIndex);
    }

    @Override
    public PlayerMove chooseMove(Board board, ArrayList<PlayerMove> playerMoves) {
        if(playerMoves.size() == 0) {
            return null;
        }

        return playerMoves.get(new Random().nextInt(playerMoves.size()));
    }
}