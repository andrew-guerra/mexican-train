package libs.game;

public class Tile {
    private final int face1, face2;
    private boolean orientation;

    public Tile(int face1, int face2) {
        this.face1 = face1;
        this.face2 = face2;
        this.orientation = true;
    }

    public boolean isCompatible(int value) {
        return this.hasValue(value);
    }

    public boolean isCompatible(Tile tile) {
        return tile.orientation ? this.isCompatible(tile.face2) : this.isCompatible(tile.face1);
    }

    public boolean hasValue(int value) {
        return this.face1 == value || this.face2 == value;
    }

    public boolean isDouble() {
        return this.face1 == this.face2;
    }

    public int getScore() {
        if(this.face1 == 0 && this.face2 == 0) {
            return 50;
        }

        return this.face1 + this.face2;
    }

    public void orient(int value) {
        if(this.face1 == value) {
            this.orientation = true;
        } else if(this.face2 == value) {
            this.orientation = false;
        }
    }

    public void orient(Tile tile) {
        if(tile.orientation) {
            orient(tile.face2);
        } else {
            orient(tile.face1);
        }
    }

    public void flipOrientation() {
        this.orientation = !this.orientation;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Tile)) {
            return false;
        }

        Tile tileObj = (Tile) obj;

        if(this.isDouble() || tileObj.isDouble()){
            return this.hasValue(face1);
        }
        
        return this.hasValue(tileObj.face1) && this.hasValue(face2);
    }

    @Override
    public String toString() {
        int primaryFace = this.orientation ? this.face1 : this.face2;
        int secondaryFace = this.orientation ? this.face2 : this.face1;
        
        return String.format("(%d,%d)", primaryFace, secondaryFace);
    }
}