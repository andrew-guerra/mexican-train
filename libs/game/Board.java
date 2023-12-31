package libs.game;

import java.util.ArrayList;

public class Board {
    private final Train[] trains;

    public Board(Train[] trains) {
        this.trains = trains;
    }

    public ArrayList<PlayerMove> generateMoves(Tile tile, int playerIndex) {
        ArrayList<PlayerMove> moves = new ArrayList<>();

        for(Train train : this.trains) {
            if((train.isPublic() || train.getTrainIndex() == playerIndex) && train.isPlayible(tile)) {
                moves.add(new PlayerMove(tile, train.getTrainIndex(), playerIndex));
            }
        }

        return moves;
    }

    public boolean isPlayibleByPlayer(PlayerMove playerMove) {
        return trains[playerMove.trainIndex].isPublic() || (playerMove.trainIndex == playerMove.playerIndex);
    }

    public boolean isLegalMove(PlayerMove playerMove) {
        return isPlayibleByPlayer(playerMove) && trains[playerMove.trainIndex].isPlayible(playerMove.tile);
    }

    public void makeMove(PlayerMove playerMove) {
        if(!isLegalMove(playerMove)) {
            return;
        }

        trains[playerMove.trainIndex].play(playerMove.tile);
    }

    public boolean isPublic(int trainIndex) {
        return this.trains[trainIndex].isPublic();
    }

    public void setIsPublic(int trainIndex, boolean isPublic) {
        this.trains[trainIndex].setIsPublic(isPublic);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Train train : this.trains) {
            stringBuilder.append("[");
            stringBuilder.append(train.toString());
            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }
}