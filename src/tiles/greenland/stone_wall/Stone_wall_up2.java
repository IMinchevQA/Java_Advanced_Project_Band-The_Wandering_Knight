package tiles.greenland.stone_wall;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Stone_wall_up2 extends Tile {

    public Stone_wall_up2(int id) {
        super(Assets.getFieldElement("stone_wall_up2"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
