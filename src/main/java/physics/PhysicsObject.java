package physics;

import math.Matrix;
import math.Vector3;

public class PhysicsObject {

    Matrix coordinates; // [c*t, x, y, z]
    Vector3 velocity;

    public PhysicsObject(Matrix coordinates, Vector3 velocity) {
        this.coordinates = coordinates;
        this.velocity = velocity;
    }


}
