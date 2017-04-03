package tiles.greenland.decorations;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Bush2 extends Tile {

    public Bush2(int id) {
        super(Assets.getFieldElement("bush2"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
