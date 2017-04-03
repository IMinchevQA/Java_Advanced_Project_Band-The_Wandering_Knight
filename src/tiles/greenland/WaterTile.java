package tiles.greenland;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class WaterTile extends Tile {

    public WaterTile(int id) {
        super(Assets.getFieldElement("water"), id);
    }

}
