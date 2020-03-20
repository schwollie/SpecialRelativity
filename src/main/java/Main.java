import display.Canvas;
import display.FpsTracker;
import display.Window;
import graphics.Camera;
import math.Vector3;
import objects.Cube;
import sandbox.World;

import java.awt.*;

public class Main {



    public static void main(String[] args) {

        World w = new World();
        Cube p =  new Cube(new Vector3(0, 0, 10), 5);
        w.objects.add(p);

        p.rotateAroundCenter(Math.PI/4, Math.PI/8, 0);
        //p.rotateAroundCenter(Math.PI/4, 0, 0);

        Camera cam = new Camera();

        Window window = new Window(600, 600);
        Canvas c = new Canvas();
        window.add(c);

        c.cam = cam;
        c.w = w;

        FpsTracker tracker = new FpsTracker(60);

        while (true) {

            tracker.stepForward();
            tracker.waitForTargetFPS();
            System.out.println(tracker);
            EventQueue.invokeLater(c::repaint);
        }


    }

}
