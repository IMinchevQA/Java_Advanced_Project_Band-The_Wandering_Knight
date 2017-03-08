package entities.creature.projectile;

import entities.Entity;
import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;


/**
 * Created by Home on 3/7/2017.
 */
public class Projectile extends Entity{

    protected double angle;
    protected float originX, originY;
    protected int speed, rate, range, damage;

    protected double nx, ny;

    public Projectile(Handler handler, float x, float y, double dir){
        super(handler, x + 20, y + 20, 26, 24);
        this.health = DEFAULT_HEALTH;
        this.angle = dir;
        this.range = 300;
        this.damage = 50;
        this.rate = 15;
        this.speed = 4;
        this.originX = x;
        this.originY = y;
        this.bounds.x = 1;
        this.bounds.y = 1;
        this.bounds.width = 1;
        this.bounds.height = 1;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);

    }
    @Override
    public void tick() {
        move();
        checkContact();
    }

    private void checkContact() {
        Rectangle cb = getCollisionBounds(0, 0);
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(cb)) {
                e.hurt(damage);
                this.setActive(false);
                return;
            }
        }
    }


    protected void move(){
        if(Math.abs(originX - x) > this.range || Math.abs(originY - y) > this.range){
            this.setActive(false);
        }
        if (!checkEntityCollisions((float) nx, 0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0f, (float) nx)) {
            moveY();
        }
    }

    public void moveX(){
        if(nx > 0) {//Move right
            //Tile (Index in the world matrix)WHERE THE HERO/CREATURE IS POSITIONED AT MOMENT
            int tx = (int) (x + nx + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += nx;
            } else {
                setActive(false);
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width -1;
            }

        } else if(nx < 0) { //Move left
            //Tile (Index in the world matrix)WHERE THE HERO/CREATURE IS POSITIONED AT MOMENT
            int tx = (int) (x + nx + bounds.x) / Tile.TILE_WIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += nx;
            } else {
                setActive(false);
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY(){
        if(ny < 0) { //Move up
            int ty = (int) (y + ny + bounds.y) / Tile.TILE_HEIGHT;
            if(!collisionWithTile((int)(x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += ny;
            } else {
                setActive(false);
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (ny > 0) { //Move down
            int ty = (int) (y + ny + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if(!collisionWithTile((int)(x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += ny;
            } else {
                setActive(false);
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.magic, (int) (this.x - handler.getGameCamera().getxOffset()), (int) (this.y - handler.getGameCamera
                ().getyOffset()), this.width, this.height, null);
    }

    @Override
    public void die() {

    }
}
