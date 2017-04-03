package tiles.desert;

import gfx.Assets;
import tiles.Tile;

public class Sand extends Tile {

    public Sand(int id) {
        super(Assets.getFieldElement("sand"), id);
    }

}
