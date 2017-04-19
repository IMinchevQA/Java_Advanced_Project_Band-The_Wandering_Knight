package entities.creature.projectile;

import entities.EntityImpl;
import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class Projectile extends EntityImpl {

    private static final int DEFAULT_HEALTH = 3;
    private double angle;
    private float originX, originY;
    private int speed, rate, range, damage;

    private double nx, ny;

    public Projectile(Handler handler, float x, float y, double dir){
        super(handler, x + 20, y + 20, 26, 24);
        super.setHealth(DEFAULT_HEALTH);
        this.angle = dir;
        this.range = 300;
        this.damage = 50;
        this.rate = 15;
        this.speed = 4;
        this.originX = x;
        this.originY = y;
        super.getBoundsRect().setBounds(1, 1, 1, 1);
        this.nx = this.speed * Math.cos(this.angle);
        this.ny = this.speed * Math.sin(this.angle);

    }
    @Override
    public void tick() {
        move();
        checkContact();
    }

    private void checkContact() {
        Rectangle cb = getCollisionBounds(0, 0);
        for (EntityImpl e : super.getHandler().getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(cb)) {
                e.hurt(this.damage);
                this.setActive(false);
                return;
            }
        }
    }


    private void move(){
        if(Math.abs(this.originX - super.getX()) > this.range || Math.abs(this.originY - super.getY()) > this.range){
            this.setActive(false);
        }
        if (!checkEntityCollisions((float) this.nx, 0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0f, (float) this.nx)) {
            moveY();
        }
    }

    private void moveX(){
        if(this.nx > 0) {//Move right
            //Tile (Index in the world matrix)WHERE THE HERO/CREATURE IS POSITIONED AT MOMENT
            int tx = (int) (super.getX() + this.nx + super.getBoundsRect().getX() + super.getBoundsRect().getWidth()) / Tile.getTileWidth();
            if(!collisionWithTile(tx, (int) (super.getY() + super.getBoundsRect().getY()) / Tile.getTileHeight()) &&
                    !collisionWithTile(tx, (int) (super.getY() + super.getBoundsRect().getY() + super.getBoundsRect().getHeight()) / Tile.getTileHeight())){
                super.setX(super.getX() + (float) this.nx);
            } else {
                setActive(false);
                super.setX(tx * Tile.getTileWidth() - (int) (super.getBoundsRect().getX() - super.getBoundsRect().getWidth()) - 1);
            }

        } else if (this.nx < 0) { //Move left
            //Tile (Index in the world matrix)WHERE THE HERO/CREATURE IS POSITIONED AT MOMENT
            int tx = (int) (super.getX() + this.nx + super.getBoundsRect().getX()) / Tile.getTileWidth();
            if(!collisionWithTile(tx, (int) (super.getY() + super.getBoundsRect().getY()) / Tile.getTileHeight()) &&
                    !collisionWithTile(tx, (int) (super.getY() + super.getBoundsRect().getY() + super.getBoundsRect().getHeight()) / Tile.getTileHeight())){
                super.setX(super.getX() + (float) this.nx);
            } else {
                setActive(false);
                super.setX(tx * Tile.getTileWidth() + Tile.getTileWidth() - (int) super.getBoundsRect().getX());
            }
        }
    }

    public void moveY(){
        if(this.ny < 0) { //Move up
            int ty = (int) (super.getY() + this.ny + super.getBoundsRect().getY()) / Tile.getTileHeight();
            if(!collisionWithTile((int)(super.getX() + super.getBoundsRect().getX()) / Tile.getTileWidth(), ty) &&
                    !collisionWithTile((int)(super.getX() + super.getBoundsRect().getX() + super.getBoundsRect().getWidth()) / Tile.getTileWidth(), ty)) {
                super.setY(super.getY() + (float) this.ny);
            } else {
                setActive(false);
                super.setY(ty * Tile.getTileHeight() + Tile.getTileHeight() - (int) super.getBoundsRect().getY());
            }
        } else if (this.ny > 0) { //Move down
            int ty = (int) (super.getY() + this.ny + super.getBoundsRect().getY() + super.getBoundsRect().getHeight()) / Tile.getTileHeight();
            if(!collisionWithTile((int)(super.getX() + super.getBoundsRect().getX()) / Tile.getTileWidth(), ty) &&
                    !collisionWithTile((int) (super.getX() + super.getBoundsRect().getX() + super.getBoundsRect().getWidth()) / Tile.getTileWidth(), ty)){
                this.setY(this.getY() + (float) this.ny);
            } else {
                setActive(false);
                super.setY(ty * Tile.getTileHeight() - (int) (super.getBoundsRect().getY() - super.getBoundsRect().getHeight()) - 1);
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return super.getHandler().getWorld().getTile(x, y).isSolid();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getFieldElement("magic"), (int) (super.getX() - super.getHandler().getGameCamera().getxOffset()), (int) (super.getY() - super.getHandler().getGameCamera
                ().getyOffset()), super.getWidth(), super.getHeight(), null);
    }

}
