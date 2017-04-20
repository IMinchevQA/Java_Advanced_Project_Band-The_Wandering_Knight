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
        this.entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick(){
        Iterator<EntityImpl> it = this.entities.iterator();
        while (it.hasNext()){
            EntityImpl e = it.next();
            e.tick();
            if(!e.isActive()){
                it.remove();
            }
        }
        this.entities.sort(this.renderSort);
    }

    public void render(Graphics g){
        for (EntityImpl entityImpl : this.entities) {
            entityImpl.render(g);
        }
    }

    public void addEntity(EntityImpl e){
        this.entities.add(e);
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<EntityImpl> getEntities() {
        return this.entities;
    }

    public void setSavedPlayer(Player player) {
        this.entities.set(0, player);
        this.player = player;
    }
}
