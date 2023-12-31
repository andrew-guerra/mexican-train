package libs.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
    private final ArrayList<Tile> tiles;

    public Hand(Tile[] list) {
        this.tiles = new ArrayList<>(Arrays.asList(list));
    }

    public Hand() {
        this.tiles = new ArrayList<>();
    }

    public void add(Tile tile) {
        tiles.add(tile);
    }

    public void remove(Tile tile) {
        tiles.remove(tile);
    }

    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    public boolean isEmpty() {
        return this.tiles.size() == 0;
    }

    public int getScore() {
        int score = 0;

        for(Tile tile : tiles) {
            score += tile.getScore();
        }

        return score;
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
