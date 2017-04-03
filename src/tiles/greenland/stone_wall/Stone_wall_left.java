package tiles.greenland.stone_wall;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Stone_wall_left extends Tile {

    public Stone_wall_left(int id) {
        super(Assets.getFieldElement("stone_wall_left"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
