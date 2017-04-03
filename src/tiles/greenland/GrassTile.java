package tiles.greenland;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class GrassTile extends Tile {

    public GrassTile(int id) {
        super(Assets.getFieldElement("grass"), id);
    }

}
