package sandbox;

import graphics.Ray;
import graphics.RayIntersection;
import objects.WorldObject;

import java.util.LinkedList;

public class World {

    public LinkedList<WorldObject> objects = new LinkedList<WorldObject>();

    public LinkedList<RayIntersection> getRayIntersections(Ray r) {
        LinkedList<RayIntersection> rayIntersections = new LinkedList<RayIntersection>();

        for (WorldObject o : objects) {
            rayIntersections.addAll(o.rayHits(r));
        }

        return rayIntersections;
    }

}
