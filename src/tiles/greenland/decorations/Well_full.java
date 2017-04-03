package tiles.greenland.decorations;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Well_full extends Tile {

    public Well_full(int id) {
        super(Assets.getFieldElement("well_full"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
