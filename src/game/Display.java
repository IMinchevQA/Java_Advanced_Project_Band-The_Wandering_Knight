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
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();

    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
