package physics;

import math.Matrix;
import math.Vector2;

public abstract class LorentzTransform {


    // Matrix:  { y        -yBnx        -yBny        -yBnz       }   in 2D it is just a 3*3 Matrix
    //          { -yBnx    1+(y-1)nx²   (y-1)nx*ny   (y-1)nx*nz  }
    //          { -yBny    (y-1)nx*ny   1+(y-1)ny²   (y-1)ny*nz  }
    //          { -yBnz    (y-1)nx*nz   (y-1)nz*ny   1+(y-1)nz²  }
    //
    //  y = 1/root(1-(v²/c²));
    //  B = v/c;
    //  n = unit Vector of velocity

    public static Matrix doLorentzTransform(Matrix X, Vector2 v) {
        Matrix lM = getLorentzMatrix(X, v);
        Matrix newX = lM.matmul(X.transpose());
        newX.show();
        return newX;
    }

    public static Matrix getLorentzMatrix(Matrix X, Vector2 v) {
        double vL = v.getLength();
        Vector2 vn = v.getNormalized();
        double nx = vn.getX(), ny = vn.getY();

        double B = vL / Consts.c;
        double y = 1/Math.sqrt( 1 - (B*B) );

        Matrix lM = new Matrix( new double[][]
                {
                        {  y,         -y*B*nx,         -y*B*ny     },
                        { -y*B*nx,    1+(y-1)*nx*nx,   (y-1)*nx*ny },
                        { -y*B*ny,    (y-1)*nx*ny,   1+(y-1)*ny*ny }
                }
        );

        return lM;
    }

    public static void main(String[] args) {
        Matrix X = new Matrix( new double[][] { {1*Consts.c, 10000, 10000} });
        Vector2 v = new Vector2(0, 100000);
        Matrix m = doLorentzTransform(X, v);

        System.out.println(m.getAt(0, 0) / Consts.c );
    }


}
