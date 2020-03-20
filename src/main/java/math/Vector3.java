package math;

public class Vector3 {

    public static final Vector3 zero = new ImmutableVector3(0, 0, 0);
    public static final Vector3 ones = new ImmutableVector3(1, 1, 1);

    private double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {}

    public Vector3(Vector3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public void setValues(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void normalize() {
        double len = getLength();
        if (len == 0) { return; }

        double factor = 1/len;
        this.x = x*factor;
        this.y = y*factor;
        this.z = z*factor;
    }

    public Vector3 getNormalized() {
        Vector3 newV = new Vector3(this.x, this.y, this.z);
        newV.normalize();
        return newV;
    }

    public double getLength() {
        return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
    }

    public double getLengthSquared() {
        return (this.x*this.x + this.y*this.y + this.z*this.z);
    }

    public Vector3 add(Vector3 v) {
        return new Vector3(this.x + v.getX(), this.y + v.getY(), this.z + v.getZ());
    }

    public Vector3 subtract(Vector3 v) { return new Vector3(this.x - v.getX(), this.y - v.getY(), this.z - v.getZ()); }

    public Vector3 rowWiseMultiplication(Vector3 other) {
        return new Vector3(this.getX()*other.getX(),  this.getY()*other.getY(), this.getZ()*other.getZ());
    }

    public Vector3 scalarMult(double a) {
        return new Vector3(a*this.getX(), a*this.getY(), a*this.getZ());
    }

    public double dotProduct(Vector3 other) {
        return other.getX() * this.getX() + other.getY() * this.getY() + this.getZ() * other.getZ();
    }

    public static Vector3 crossProduct(Vector3 a, Vector3 b) {
        double newX = a.y*b.z - b.y*a.z; // a2*b3 - b2*a3
        double newY = a.z*b.x - b.z*a.x; // a3*b1 - b3*a1
        double newZ = a.x*b.y - b.x*a.y; // a1*b2 - b1*a2
        return new Vector3(newX, newY, newZ);
    }

    public static double dotProduct(Vector3 a, Vector3 b) {
        return a.dotProduct(b);
    }

    public double getDistance(Vector3 other) {
        return Math.sqrt( Math.pow(other.getX() - x, 2) + Math.pow(other.getY() - y, 2) + Math.pow(other.getZ() - z, 2));
    }

    public static Vector3 vectorFromPoints(Vector3 pointA, Vector3 pointB) {
        // creates a Vector from A to B
        return new Vector3(pointB.x - pointA.x, pointB.y - pointA.y, pointB.z - pointA.z);
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setZ(double z) {
        this.z = z;
    }

    public double getX() { return this.x; }
    public double getY() { return this.y; }
    public double getZ() {
        return z;
    }

    public void print() {
        System.out.println("(" + this.getX() + " | " + this.getY() + " | " + this.getZ() + ")" + "    Length: " + this.getLength());
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector3) {
            Vector3 other = (Vector3)obj;
            if (other.getX()==this.x && other.getY() == this.y && other.getZ() == this.z) { return true; }
            return false;
        } else { return false; }
    }
}

class ImmutableVector3 extends Vector3 {

    public ImmutableVector3(double x, double y, double z) {
        super(x, y, z);
    }

    @Override
    public void normalize() {
        throw new IllegalStateException("Read only");
    }

    @Override
    public void setX(double x) {
        throw new IllegalStateException("Read only");
    }

    @Override
    public void setY(double y) {
        throw new IllegalStateException("Read only");
    }

    @Override
    public void setZ(double z) {
        throw new IllegalStateException("Read only");
    }

    @Override
    public void setValues(double x, double y, double z) {
        throw new IllegalStateException("Read only");
    }
}

