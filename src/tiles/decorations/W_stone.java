package tiles.decorations;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class W_stone extends Tile {

    public W_stone(int id) {
        super(Assets.w_stone, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}