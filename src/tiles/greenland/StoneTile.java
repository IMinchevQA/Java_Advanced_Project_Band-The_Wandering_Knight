package tiles.greenland;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class StoneTile extends Tile {

    public StoneTile(int id) {
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
