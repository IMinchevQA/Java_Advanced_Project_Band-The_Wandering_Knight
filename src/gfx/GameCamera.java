package gfx;

import entities.EntityImpl;
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
        if (this.xOffset < 0) {
            this.xOffset = 0;
            //handler.getWidth() - CURRENT WINDOW WIDTH IN PIXELS
        } else if (this.xOffset > this.handler.getWorld().getWidth() * Tile.getTileWidth()- this.handler.getWidth()) {
            this.xOffset = this.handler.getWorld().getWidth() * Tile.getTileWidth() - this.handler.getWidth();
        }
        if (this.yOffset < 0) {
            this.yOffset = 0;
        } else if (this.yOffset > this.handler.getWorld().getHeight() * Tile.getTileHeight() - this.handler.getHeight()){
            this.yOffset = this.handler.getWorld().getHeight() * Tile.getTileHeight() - this.handler.getHeight();
        }

    }

    public void centerOnEntity(EntityImpl e){
        this.xOffset = e.getX() - this.handler.getWidth() / 2  + e.getWidth() / 2;
        this.yOffset = e.getY() - this.handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    // xAmt, yAmt respectively - move amount by axis x, and axis y
    public void move(float xAmt, float yAmt){
        this.xOffset += xAmt;
        this.yOffset += yAmt;
        checkBlankSpace();
    }

    public float getxOffset() {
        return this.xOffset;
    }

    public float getyOffset() {
        return this.yOffset;
    }



}
