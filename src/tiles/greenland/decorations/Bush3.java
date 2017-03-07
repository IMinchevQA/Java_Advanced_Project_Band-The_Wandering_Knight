package tiles.decorations;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Bush3 extends Tile {

    public Bush3(int id) {
        super(Assets.bush3, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
