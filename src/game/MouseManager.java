package game;

import gfx.UI.AboutManager;
import gfx.UI.PauseManager;
import gfx.UI.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private  int mouseX, mouseY;

    //ADD A VARIABLE TO RELATE EVENTS mouseRelease and mouseMoved with UIManager's events onMouseMove and onMouseRelease!!!
    private UIManager uiManager;
    private AboutManager aboutManager;
    private PauseManager pauseManager;

    public MouseManager(){

    }

    public PauseManager getPauseManager() {
        return this.pauseManager;
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
        return this.uiManager;
    }

    // Getters
    public int getMouseX(){
        return this.mouseX;
    }
    public int getMouseY(){
        return this.mouseY;
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
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.leftPressed = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.leftPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightPressed = false;
        }

        if (this.uiManager != null) {
            this.uiManager.onMouseRelease(e);
        }

        if (this.aboutManager != null) {
            this.aboutManager.onMouseRelease(e);
        }

        if (this.pauseManager != null) {
            this.pauseManager.onMouseRelease(e);
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

    //mouseMove listener
    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();

        if (this.uiManager != null) {
            this.uiManager.onMouseMove(e);
        }

        if (this.aboutManager != null) {
            this.aboutManager.onMouseMove(e);
        }

        if (this.pauseManager != null){
            this.pauseManager.onMouseMove(e);
        }
    }
}
