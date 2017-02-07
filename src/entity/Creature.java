package entity;

//ABSTRACT CREATURE IN CASE WE ADD ITEMS AND OTHER NON CREATURE ENTITIES

public abstract class Creature extends Entity {

    //CREATURE VARIABLES GO HERE HEALTH DAMAGE ARMOR ETC ETC
    protected int health;

    public Creature(float x, float y) {
        super(x, y);
    }
}
