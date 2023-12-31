package libs.game;

import java.util.Stack;
import java.util.ArrayList;

public class Train {
    private final Stack<Tile> tiles;
    private final int startValue;
    private final int trainIndex;
    private boolean isPublic;

    public Train(int startValue, int trainIndex) {
        this.tiles = new Stack<>();
        this.startValue = startValue;
        this.trainIndex = trainIndex;
        this.isPublic = false;
    }

    public boolean isPlayible(Tile tile, boolean staticOrientation) {
        if(tile == null) {
            return false;
        }

        if(this.isEmpty()) {
            return staticOrientation ? this.startValue == tile.getFirstFace() : tile.isCompatible(this.startValue);
        }

        return staticOrientation ? this.tiles.peek().getSceondFace() == tile.getFirstFace() : tile.isCompatible(this.tiles.peek());
    }

    public boolean isPlayible(Tile tile) {
        return this.isPlayible(tile, false);
    }

    public boolean isMergeable(Train train) {
        if(train == null) {
            return false;
        }

        if(train.isEmpty()) {
            return true;
        }

        return this.isPlayible(train.tiles.firstElement(), true);
    }

    public Tile[] playible(Tile[] tiles) {
        ArrayList<Tile> playibleTiles = new ArrayList<>();

        for(Tile tile : tiles) {
            if(this.isPlayible(tile)) {
                playibleTiles.add(tile);
            }
        }

        return (Tile[]) playibleTiles.toArray(new Tile[playibleTiles.size()]);
    }

    public Train[] mergeable(Train[] trains) {
        ArrayList<Train> playibleTrains = new ArrayList<>();

        for(Train train : trains) {
            if(this.isMergeable(train)) {
                playibleTrains.add(train);
            }
        }

        return (Train[]) playibleTrains.toArray(new Train[playibleTrains.size()]);
    }

    public void play(Tile tile) {
        if(tile == null || !this.isPlayible(tile)) {
            return;
        }

        if(this.isEmpty()) {
            tile.orient(this.startValue);
        } else {
            tile.orient(this.tiles.peek());
        }

        this.tiles.push(tile);
    }

    public void merge(Train train) {
        if(train == null || !this.isMergeable(train)) {
            return;
        }

        this.tiles.addAll(train.tiles);
    }

    public boolean isEmpty() {
        return this.tiles.size() == 0;
    }

    public int getTrainIndex() {
        return trainIndex;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Tile tile : this.tiles) {
            stringBuilder.append(tile.toString());
        }

        return stringBuilder.toString();
    }
}