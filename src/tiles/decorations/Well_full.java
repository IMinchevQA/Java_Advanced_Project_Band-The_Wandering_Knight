package tiles.decorations;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Well_full extends Tile {

    public Well_full(int id) {
        super(Assets.well_full, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}