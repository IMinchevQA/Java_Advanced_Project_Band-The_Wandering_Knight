package tiles.iceland;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class SnowyBush extends Tile {

    public SnowyBush(int id) {
        super(Assets.getFieldElement("snowyBush"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
