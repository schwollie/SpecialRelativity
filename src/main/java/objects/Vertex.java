package objects;

import javafx.geometry.Point3D;
import math.Vector3;

import java.awt.geom.Point2D;

//Vertex:  a point in space from which surfaces consist of
public class Vertex {

    private Vector3 position;

    public Vertex(Vector3 position) {
        this.position = position;
    }

    public Vertex(double x, double y, double z) {
        this.position = new Vector3(x, y, z);
    }

    public Vector3 getPos() {
        return position;
    }

    public void scaleByOrigin(double factor) {
        this.position = this.position.scalarMult(factor);
    }

    public void moveVertex(Vector3 pos) {
        this.position = this.position.add(pos);
    }

    public void setPosition(Vector3 pos) { this.position = pos; }

    public Point3D getAsPoint() {
        return new Point3D(position.getX(), position.getY(), position.getZ());
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                '}';
    }
}
