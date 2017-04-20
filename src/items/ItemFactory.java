package items;

import game.Handler;
import gfx.Assets;

/**
 * Created by Home on 4/20/2017.
 */
public class ItemFactory {
    public static Item createNew(String itemName, int x, int y, Handler handler){
        Itemable itemArgs = Itemable.valueOf(itemName.toUpperCase());
        Item i = new Item(Assets.getFieldElement(itemArgs.getAsset()), itemArgs.getName(), itemArgs.getId(), x, y, handler);
        return i;
    }
}
