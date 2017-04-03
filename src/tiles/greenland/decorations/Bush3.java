package tiles.greenland.decorations;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Bush3 extends Tile {

    public Bush3(int id) {
        super(Assets.getFieldElement("bush3"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
