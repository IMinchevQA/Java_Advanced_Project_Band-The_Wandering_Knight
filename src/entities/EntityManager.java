package entities;

import entities.creature.Player;
import game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSort = (a, b) -> {
        if (a.getY() + a.getHeight() < b.getY() + b.getHeight()){
            return -1;
        }
        return 1;
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick(){
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()){
            Entity e = it.next();
            e.tick();
            if(!e.isActive()){
                it.remove();
            }
        }
        entities.sort(renderSort);
    }

    public void render(Graphics g){
        for (Entity entity : entities) {
            entity.render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    private Handler getHandler() {
        return handler;
    }

    private void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    private void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
