package entities;


import java.awt.*;

public interface Entity {

    void tick();

    void render(Graphics g);

    void die();
}
