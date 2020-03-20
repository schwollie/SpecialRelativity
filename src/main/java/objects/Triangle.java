package objects;

import graphics.Ray;
import graphics.RayIntersection;
import math.Vector3;
import sandbox.Consts;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Triangle {

    private Vertex[] vertices;
    // 3 Vectors forming a circle from vertex to vertex
    private Vector3[] vectors = new Vector3[3];


    private Vector3 normal;

    // Color of this surface
    Random rnd = new Random();
    private Color color = new Color(rnd.nextInt(255), rnd.nextInt(255),rnd.nextInt(255));


    public Triangle(Vertex[] vertices) {
        if (vertices.length != 3) { throw new IndexOutOfBoundsException("Length of Vertex Array is not 3!"); }

        this.vertices = vertices;
        this.setUpVectors();
        this.normal = getNormal();
    }

    public void setUpVectors() {
        vectors[0] = Vector3.vectorFromPoints(vertices[0].getPos(), vertices[1].getPos());
        vectors[1] = Vector3.vectorFromPoints(vertices[1].getPos(), vertices[2].getPos());
        vectors[2] = Vector3.vectorFromPoints(vertices[2].getPos(), vertices[0].getPos());
    }


    public Vector3 getNormal() {
        return Vector3.crossProduct(vectors[0], vectors[1]);
    }

    public Color getColor() { return this.color; }

    public void setColor(Color color) {
        this.color = color;
    }

    // get 2 triangles which build a rectangular face
    // must get points clockwise or counter clockwise in order!

    public static Triangle[] getRectTriangles(Vertex a, Vertex b, Vertex c, Vertex d) {
        //     b -- a
        //     |    |
        //     c -- d
        Triangle triangle1 = new Triangle(new Vertex[] { d, a, b });
        Triangle triangle2 = new Triangle(new Vertex[] { b, c, d });
        return new Triangle[] { triangle1, triangle2 };
    }


    public RayIntersection rayHit(Ray r) {
        // Moller Trumbore Algorithm from Wikipedia
        RayIntersection rayIntersection = new RayIntersection(); // is default false

        Vertex vertex0 = this.vertices[0];

        double a, f, u, v;

        Vector3 edge1 = vectors[0];
        Vector3 edge2 = vectors[2].scalarMult(-1);
        Vector3 h = Vector3.crossProduct(r.getDir(), edge2);
        a = edge1.dotProduct(h);

        if (a > -Consts.EPSILON && a < Consts.EPSILON) {
            return rayIntersection;    // This ray is parallel to this triangle.
        }

        f = 1.0 / a;
        Vector3 s = r.getOrigin().subtract(vertex0.getPos());

        u = f * ( s.dotProduct(h));

        if (u < 0.0 || u > 1.0) {
            return rayIntersection;
        }

        Vector3 q = Vector3.crossProduct(s, edge1);

        v = f * r.getDir().dotProduct(q);

        if (v < 0.0 || u + v > 1.0) {
            return rayIntersection;
        }

        // At this stage we can compute t to find out where the intersection point is on the line.
        double t = f * edge2.dotProduct(q);

        if (t > Consts.EPSILON) // ray intersection
        {
            Vector3 relativeIntersectionPoint = r.getDir().scalarMult(t); // Collision point relative to camera
            rayIntersection.doesCollide = true;
            rayIntersection.collisionPoint = r.getOrigin().add(relativeIntersectionPoint); // Collision point in world space
            rayIntersection.colorAtCollision = this.color;
            rayIntersection.Distance2RayOrigin = relativeIntersectionPoint.getLength();
            return rayIntersection;

        } else // This means that there is a line intersection but not a ray intersection.
        {
            return rayIntersection;
        }
    }


    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + Arrays.toString(vertices) +
                ", vectors=" + Arrays.toString(vectors) +
                ", normal=" + normal +
                ", color=" + color +
                '}';
    }

    //Test things
    public static void main(String[] args) {
        //test cross product
        Triangle s1 = new Triangle(new Vertex[] { new Vertex(0, 0, 1), new Vertex(1.5, 3, 1), new Vertex(5, 0, 1)   });
        //s1.getNormal().print();

        //test intersection

        Ray r = new Ray(new Vector3(0, 0, 0), new Vector3(0, 0, 1));
        //System.out.println(s1.hitsRay(r));
        long s = System.currentTimeMillis();

        double size = 1; // less is more :)

        for (double y = 5; y > 0; y-=0.16*size) {

            String l = "";

            for (double x = 0; x < 5; x+=0.03*size) {

                r = new Ray(new Vector3(x, y, -1.59), new Vector3(0, 0, 1));

                if (s1.rayHit(r).doesCollide) {
                    l += "X";
                    //System.out.println(s1.hitsRay(r).Distance2RayOrigin);
                } else {
                    l+=" ";
                }
            }

            System.out.println(l);
        }

        long e = System.currentTimeMillis();

        System.out.println(e-s);
    }




}
