package tiles.greenland.stone_wall;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Stone_wall_up1 extends Tile {

    public Stone_wall_up1(int id) {
        super(Assets.getFieldElement("stone_wall_up1"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
