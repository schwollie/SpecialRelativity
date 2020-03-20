package math;

import java.awt.geom.AffineTransform;

public class Transform3D {

    private Matrix xR = Matrix.identity(3);
    private Matrix yR = Matrix.identity(3);
    private Matrix zR = Matrix.identity(3);

    private Vector3 translationOffset = new Vector3(0, 0, 0);

    private double scaleFactor = 1;

    public Vector3 transformPoint(Vector3 point) {
        Vector3 result;

        result = translatePoint(point);
        result = rotatePointAroundOrigin(result);
        result = scalePointByOrigin(result);

        return result;
    }

    // region scale:

    public void setScale(double scale) {
        this.scaleFactor = scale;
    }

    private Vector3 scalePointByOrigin(Vector3 point) {
        return point.scalarMult(scaleFactor);
    }

    // endregion

    // region translation:

    public void translate(Vector3 offset) {
        translationOffset = offset;
    }

    private Vector3 translatePoint(Vector3 point) {
        return point.add(translationOffset);
    }

    // endregion

    //region rotations:
    public void rotate(double alpha, double beta, double gamma) {
        xR = getXRotationMatrix(alpha);
        yR = getYRotationMatrix(beta);
        zR = getZRotationMatrix(gamma);
    }

    private Vector3 rotatePointAroundOrigin(Vector3 point) {
        Matrix coordinates = new Matrix( new double[][] { { point.getX(), point.getY(), point.getZ() } });
        coordinates = coordinates.transpose();

        Matrix result = xR.matmul(coordinates);
        result = yR.matmul(result);
        result = zR.matmul(result);

        return new Vector3(result.getAt(0, 0), result.getAt(1, 0), result.getAt(2, 0));
    }

    //get the rotation Matrix for the X Component
    private Matrix getXRotationMatrix(double alpha) {
        double cos = Math.cos(alpha);
        double sin = Math.sin(alpha);

        Matrix m = new Matrix( new double[][] {
                {1,    0,    0},
                {0,  cos, -sin},
                {0,  sin,  cos}
        } );

        return m;
    }

    //get the rotation Matrix for the Y Component
    private Matrix getYRotationMatrix(double alpha) {
        double cos = Math.cos(alpha);
        double sin = Math.sin(alpha);

        Matrix m = new Matrix( new double[][] {
                { cos,    0,    sin},
                {   0,    1,      0},
                {-sin,    0,    cos}
        } );

        return m;
    }

    //get the rotation Matrix for the Z Component
    private Matrix getZRotationMatrix(double alpha) {
        double cos = Math.cos(alpha);
        double sin = Math.sin(alpha);

        Matrix m = new Matrix( new double[][] {
                { cos, -sin,    0},
                { sin,  cos,    0},
                {   0,    0,    1}
        } );

        return m;
    }

    //endregion

}
