package objects;

import math.Vector3;

import java.util.Arrays;

public class Plane extends WorldObject {

    private Vector3 center;

    public Plane(Vector3 center, double scale) {
        this.center = center;
        setupSurfaces(scale);
    }

    private void setupSurfaces(double scale) {
        // create corner points
        Vertex[] corners = new Vertex[] {
                new Vertex(0.5, 0.5, 0), new Vertex(-0.5, 0.5, 0),
                new Vertex(0.5, -0.5, 0), new Vertex(-0.5, -0.5, 0)
        };

        this.vertices = corners;

        // set right position and scale
        for (int i = 0; i < 4; i++) { corners[i].scaleByOrigin(scale); corners[i].moveVertex(this.center); }

        // create surfaces:

        // top & bottom surfaces:
        Triangle[] tris = Triangle.getRectTriangles(corners[0], corners[1], corners[3], corners[2]);

        this.triangles = new Triangle[] {
                tris[0], tris[1]
        };
    }

    @Override
    public String toString() {
        return "Plane{" +
                "center=" + center +
                ", triangles=" + Arrays.toString(triangles) +
                '}';
    }
}
