package game;

import gfx.UI.AboutManager;
import gfx.UI.PauseManager;
import gfx.UI.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

public class MouseManager implements MouseListener, MouseMotionListener{

    private boolean leftPressed, rightPressed;
    private  int mouseX, mouseY;

    //ADD A VARIABLE TO RELATE EVENTS mouseRelease and mouseMoved with UIManager's events onMouseMove and onMouseRelease!!!
    private UIManager uiManager;
    private AboutManager aboutManager;
    private PauseManager pauseManager;

    public MouseManager(){

    }

    public PauseManager getPauseManager() {
        return pauseManager;
    }

    public void setPauseManager(PauseManager pauseManager) {
        this.pauseManager = pauseManager;
    }

    public void setUIManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    public void setAboutManager(AboutManager aboutManager) {
        this.aboutManager = aboutManager;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    // Getters
    public boolean isLeftPressed(){
        return leftPressed;
    }

    public boolean isRightPressed(){
        return rightPressed;
    }

    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }

    public int[] registerMouse(){
        int[] xy = new int[2];
        if(this.leftPressed){

            xy[0] = mouseX;
            xy[1] = mouseY;
        }
        return xy;
    }

    public boolean isPressed(){
        if(this.leftPressed){
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = true;
        }else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = false;
        }else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = false;
        }

        if(uiManager != null){
            uiManager.onMouseRelease(e);
        }

        if(aboutManager != null) {
            aboutManager.onMouseRelease(e);
        }

        if(pauseManager != null){
            pauseManager.onMouseRelease(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null){
            uiManager.onMouseMove(e);
        }

        if(aboutManager != null) {
            aboutManager.onMouseMove(e);
        }

        if(pauseManager != null){
            pauseManager.onMouseMove(e);
        }
    }
}
