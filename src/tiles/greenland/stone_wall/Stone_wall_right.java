package tiles.greenland.stone_wall;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Stone_wall_right extends Tile {

    public Stone_wall_right(int id) {
        super(Assets.getFieldElement("stone_wall_right"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
