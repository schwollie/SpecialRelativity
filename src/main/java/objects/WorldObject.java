package objects;

import com.sun.javafx.geom.transform.Affine3D;
import graphics.Ray;
import graphics.RayIntersection;
import javafx.geometry.Point3D;
import math.Transform3D;
import math.Vector3;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public abstract class WorldObject {

    // this is the midpoint of the Object
    protected Vector3 center;

    // all surfaces of this object
    protected Triangle[] triangles;
    protected Vertex[] vertices;

    public LinkedList<RayIntersection> rayHits(Ray r) {
        LinkedList<RayIntersection> intersections = new LinkedList<RayIntersection>();

        for (Triangle t : triangles) {
            intersections.add(t.rayHit(r));
        }
        return intersections;
    }


    public void rotateAroundCenter(double alpha, double beta, double gamma) {

        Transform3D t = new Transform3D();

        //move all points to origin
        t.translate(center.scalarMult(-1));
        t.rotate(alpha, beta, gamma);

        // apply transform to all points
        this.transform(t);

        // reverse translation
        Transform3D t2 = new Transform3D();
        t2.translate(center);

    }

    public void transform(Transform3D t) {

        // Update Vertices
        for ( Vertex v : vertices ) {
            Vector3 newPos = t.transformPoint(v.getPos());
            v.setPosition(newPos);
        }

        //reset Triangles:
        for (Triangle tri : triangles) {
            tri.setUpVectors();
        }
    }


}
