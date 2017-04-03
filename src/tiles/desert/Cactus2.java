package tiles.desert;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Cactus2 extends Tile {

    public Cactus2(int id) {
        super(Assets.getFieldElement("cactus2"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
