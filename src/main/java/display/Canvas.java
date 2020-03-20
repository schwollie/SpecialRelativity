package display;

import graphics.Camera;
import sandbox.World;

import javax.swing.*;
import java.awt.*;


public class Canvas extends JPanel {

    public Camera cam;
    public World w;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D)g;
        cam.RenderTest(w, g2d);
    }

}

