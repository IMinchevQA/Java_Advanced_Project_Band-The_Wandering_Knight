package entities;

import entities.creature.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {


    private Player player;
    private ArrayList<EntityImpl> entities;
    private Comparator<EntityImpl> renderSort = (a, b) -> {
        if (a.getY() + a.getHeight() < b.getY() + b.getHeight()){
            return -1;
        }
        return 1;
    };

    public EntityManager(Player player) {
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick(){
        Iterator<EntityImpl> it = entities.iterator();
        while (it.hasNext()){
            EntityImpl e = it.next();
            e.tick();
            if(!e.isActive()){
                it.remove();
            }
        }
        entities.sort(renderSort);
    }

    public void render(Graphics g){
        for (EntityImpl entityImpl : entities) {
            entityImpl.render(g);
        }
    }

    public void addEntity(EntityImpl e){
        entities.add(e);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<EntityImpl> getEntities() {
        return entities;
    }
}
