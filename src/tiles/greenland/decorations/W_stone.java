package tiles.greenland.decorations;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class W_stone extends Tile {

    public W_stone(int id) {
        super(Assets.getFieldElement("w_stone"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}