package libs.game;

public class PlayerMove {
    public final Tile tile;
    public final int trainIndex, playerIndex;

    public PlayerMove(Tile tile, int trainIndex, int playerIndex) {
        this.tile = tile;
        this.trainIndex = trainIndex;
        this.playerIndex = playerIndex;
    }

    @Override
    public String toString() {
        return String.format("tile: %s train: %d player: %d", this.tile.toString(), this.trainIndex, this.playerIndex);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof PlayerMove)) {
            return false;
        }

        PlayerMove otherPlayerMove = (PlayerMove) obj;

        return this.tile.equals(otherPlayerMove.tile) && this.trainIndex == otherPlayerMove.trainIndex && this.playerIndex == otherPlayerMove.playerIndex;
    }
}
