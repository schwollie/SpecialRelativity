package objects;

import math.Vector3;

public class Cube extends WorldObject {

    public Cube(Vector3 center, double scale) {
        this.center = center;
        setupSurfaces(scale);
    }

    private void setupSurfaces(double scale) {
        // create corner points
        Vertex[] corners = new Vertex[] {
                new Vertex(0.5, 0.5, 0.5), new Vertex(-0.5, 0.5, 0.5),    // top right/left far
                new Vertex(0.5, 0.5, -0.5), new Vertex(-0.5, 0.5, -0.5),  // top right/left near
                new Vertex(0.5, -0.5, 0.5), new Vertex(-0.5, -0.5, 0.5),  // bottom right/left far
                new Vertex(0.5, -0.5, -0.5), new Vertex(-0.5, -0.5, -0.5) // bottom right/left near
        };

        this.vertices = corners;

        // set right position and scale
        for (int i = 0; i < 8; i++) { corners[i].scaleByOrigin(scale); corners[i].moveVertex(this.center); }

        // create surfaces:

        // top & bottom surfaces:
        Triangle[] topTri = Triangle.getRectTriangles(corners[0], corners[1], corners[3], corners[2]);
        Triangle[] bottomTri = Triangle.getRectTriangles(corners[4], corners[5], corners[7], corners[6]);

        // left & right surfaces:
        Triangle[] rightTri = Triangle.getRectTriangles(corners[0], corners[2], corners[6], corners[4]);
        Triangle[] leftTri = Triangle.getRectTriangles(corners[1], corners[3], corners[7], corners[5]);

        // far & near surfaces:
        Triangle[] nearTri = Triangle.getRectTriangles(corners[3], corners[2], corners[6], corners[7]);
        Triangle[] farTri = Triangle.getRectTriangles(corners[1], corners[0], corners[4], corners[5]);

        this.triangles = new Triangle[] {
                topTri[0], topTri[1], bottomTri[0], bottomTri[1],
                rightTri[0], rightTri[1], leftTri[0], leftTri[1],
                nearTri[0], nearTri[1], farTri[0], farTri[1]
        };
    }


}
