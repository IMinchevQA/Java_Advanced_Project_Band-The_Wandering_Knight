package tiles.desert;

import gfx.Assets;
import tiles.Tile;

//GRASS TILE
public class Cactus1 extends Tile {

    public Cactus1(int id) {
        super(Assets.getFieldElement("cactus1"), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
