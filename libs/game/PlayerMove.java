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
}
