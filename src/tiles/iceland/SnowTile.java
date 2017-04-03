package tiles.iceland;

import gfx.Assets;
import tiles.Tile;

public class SnowTile extends Tile {

    public SnowTile(int id) {
        super(Assets.getFieldElement("snow"), id);
    }

}
