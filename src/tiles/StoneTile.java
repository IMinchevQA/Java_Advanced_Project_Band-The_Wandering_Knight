package tiles;

import gfx.Assets;

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
