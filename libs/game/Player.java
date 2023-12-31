package libs.game;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Player {
    private Hand hand;
    private final int playerIndex;
    private final int trainIndex;

    public Player(Hand hand, int playerIndex, int trainIndex) {
        this.hand = hand;
        this.playerIndex = playerIndex;
        this.trainIndex = trainIndex;
    }

    public Player(int playerIndex, int trainIndex) {
        this(new Hand(), playerIndex, trainIndex);
    }

    public abstract PlayerMove chooseMove(Board board, ArrayList<PlayerMove> playerMoves);
    
    public void draw(Stack<Tile> drawStack) {
        hand.add(drawStack.pop());
    }

    public void remove(Tile tile) {
        hand.remove(tile);
    }
    
    public int getPlayerIndex() {
        return this.playerIndex;
    }

    public int getTrainIndex() {
        return this.trainIndex;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean hasWinState() {
        return hand.isEmpty();
    }

    public final int getScore() {
        return hand.getScore();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.playerIndex);
        stringBuilder.append(":");
        stringBuilder.append(this.hand.toString());

        return stringBuilder.toString();
    }
}
