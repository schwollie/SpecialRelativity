package graphics;

import math.Vector3;

public class Ray {

    private Vector3 origin;
    private Vector3 direction;

    public Ray(Vector3 origin, Vector3 dir) {
        this.origin = origin;
        this.direction = dir;
    }

    public Vector3 getDir() {
        return direction;
    }

    public Vector3 getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }
}
