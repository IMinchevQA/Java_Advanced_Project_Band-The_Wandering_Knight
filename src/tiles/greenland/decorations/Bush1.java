package tiles.greenland.decorations;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Bush1 extends Tile {

    public Bush1(int id) {
        super(Assets.getFieldElement("bush1"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
