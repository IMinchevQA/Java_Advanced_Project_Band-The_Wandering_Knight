package game;


import javax.swing.*;
import java.awt.*;

//DISPLAY SETTINGS CLASS

public class Display {

    private JFrame frame;
    private Canvas canvas;
    private int width, height;
    private String title;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setSize(width, height);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        this.canvas = new Canvas();
        this.canvas.setMinimumSize(new Dimension(width, height));
        this.canvas.setMaximumSize(new Dimension(width, height));
        this.canvas.setPreferredSize(new Dimension(width, height));
        this.canvas.setFocusable(false);
        this.frame.add(canvas);
        this.frame.pack();

    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
