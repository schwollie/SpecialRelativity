package graphics;

import math.Vector3;
import sandbox.World;

import java.awt.*;
import java.util.LinkedList;

public class Camera {


    // All Settings for a Camera
    private Dimension resolution = new Dimension(600, 600);
    private double ratio = 1;
    private double FoV = 0.1;
    private Vector3 position = new Vector3(0, 0, -100);

    // Test demo for rendering
    public void RenderTest(World world, Graphics2D g) {
        //Image img = new Image((int)resolution.getWidth(), (int)resolution.getHeight());

        int w = (int)this.resolution.width;
        int h = (int)this.resolution.height;

        for (int i = -w/2; i < w/2; i++) {
            for (int j = -h/2; j < h/2; j++) {

                double factorX = (double) i/((double)w/2) * FoV * ratio;
                double factorY = (double) j/((double)h/2) * FoV;


                Vector3 dir = new Vector3(factorX, factorY, 1);
                //dir.normalize();

                Ray r = new Ray(position, dir);

                LinkedList<RayIntersection> rayIntersections = world.getRayIntersections(r);
                Color nearestColor = new Color(0, 0, 0);
                double nearest = 10000000;

                for (RayIntersection rI : rayIntersections) {
                    if (rI.doesCollide && rI.Distance2RayOrigin < nearest) {
                        nearest = rI.Distance2RayOrigin;
                        nearestColor = rI.colorAtCollision;
                    }
                }
                g.setColor(nearestColor);
                g.drawLine(i+(w/2), j+(h/2), i+(w/2), j+(h/2));
            }
        }

        //img.save("Test.jpg");

    }




}
