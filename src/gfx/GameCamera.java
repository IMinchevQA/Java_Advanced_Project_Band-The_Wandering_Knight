package gfx;

import entity.Entity;
import game.Handler;
import tiles.Tile;

//CLASS FOR MAKING INGAME CAMERA MOVEMENT
public class GameCamera {

    private Handler handler;
    //OFFSET VARIABLES - DEFINE HOW FAR OFF FROM THE ORIGINAL POSITION DO WE DRAW SOMETHING;
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    //METHOD THAT RESETS to ZERO (0) THE x/yOffsets(BLANK AREA BEYOND THE GAME WORLD)
    //PURPOSE - THE BLANK AREA NOT TO BE VISIBLE ON THE DISPLAY.
    public void checkBlankSpace() {
        if(xOffset < 0) {
            xOffset = 0;
            //handler.getWidth() - CURRENT WINDOW WIDTH IN PIXELS
        } else if(xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
            xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }
        if(yOffset < 0) {
            yOffset = 0;
        } else if(yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()){
            yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
        }

    }

    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getWidth() / 2  + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    // xAmt, yAmt respectively - move amount by axis x, and axis y
    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
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
