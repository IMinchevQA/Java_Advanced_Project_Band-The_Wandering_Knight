package gfx;

import entity.Entity;
import game.Game;

//CLASS FOR MAKING INGAME CAMERA MOVEMENT
public class GameCamera {

    private Game game;
    //OFFSET VARIABLES - DEFINE HOW FAR OFF FROM THE ORIGINAL POSITION DO WE DRAW SOMETHING;
    private float xOffset, yOffset;

    public GameCamera(Game game, float xOffset, float yOffset){
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnEntity(Entity e){
        xOffset = e.getX() - game.getWidth() / 2  + e.getWidth() / 2;
        yOffset = e.getY() - game.getHeight() / 2 + e.getHeight() / 2;
    }

    // xAmt, yAmt respectively - move amount by axis x, and axis y
    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public float getyOffset() {
        return yOffset;
    }



}
