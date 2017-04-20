package saves;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 4/20/2017.
 */
public class SaveObject implements Serializable{
    private HashMap<Integer, Integer> inventory;
    private boolean hasArmoe;
    private int health;
    private int currentHealth;

    public SaveObject(HashMap<Integer, Integer> inventory, boolean hasArmoe, int health, int currentHealth) {
        this.inventory = inventory;
        this.hasArmoe = hasArmoe;
        this.health = health;
        this.currentHealth = currentHealth;
    }

    public HashMap<Integer, Integer> getInventory() {
        return inventory;
    }

    public boolean isHasArmoe() {
        return hasArmoe;
    }

    public int getHealth() {
        return health;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
}
