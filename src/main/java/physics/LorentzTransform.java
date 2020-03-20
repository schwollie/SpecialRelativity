package physics;

import sandbox.Consts;
import math.Matrix;
import math.Vector3;

public abstract class LorentzTransform {


    // Boost Matrix B(v):  { y        -yBnx        -yBny        -yBnz       }   in 2D it is just a 3*3 Matrix
    //                     { -yBnx    1+(y-1)nx²   (y-1)nx*ny   (y-1)nx*nz  }
    //                     { -yBny    (y-1)nx*ny   1+(y-1)ny²   (y-1)ny*nz  }
    //                     { -yBnz    (y-1)nx*nz   (y-1)nz*ny   1+(y-1)nz²  }
    //
    //  y = 1/root(1-(v²/c²));
    //  B = v/c;
    //  n = unit Vector of velocity
    //
    //
    //  X' = B(v) * [ c*t,       <-- it is important to use c * time here!
    //                 x ,
    //                 y ,
    //                 z  ]

    public static Matrix doLorentzTransform(Matrix X, Vector3 v) {

        // X' = B(v) * X

        Matrix Bv = getLorentzMatrix(v);
        Matrix newX = Bv.matmul(X.transpose());
        //newX.show();
        return newX;
    }

    public static Matrix getLorentzMatrix(Vector3 v) {
        double vL = v.getLength();
        Vector3 vn = v.getNormalized();
        double nx = vn.getX(), ny = vn.getY(), nz = vn.getZ();

        double B = vL / Consts.c;
        double y = 1/Math.sqrt( 1 - (B*B) );

        Matrix lM = new Matrix( new double[][]
                {
                        {  y,         -y*B*nx,         -y*B*ny,         -y*B*nz     },
                        { -y*B*nx,    1+(y-1)*nx*nx,   (y-1)*nx*ny,     (y-1)*nx*nz },
                        { -y*B*ny,    (y-1)*nx*ny,     1+(y-1)*ny*ny,   (y-1)*ny*nz },
                        { -y*B*nz,    (y-1)*nx*nz,     (y-1)*nz*ny,   1+(y-1)*nz*nz }
                }
        );

        return lM;
    }

    public static void main(String[] args) {
        // Test
        Matrix X = new Matrix( new double[][] { {1*Consts.c, 0, 0, 0} });
        Vector3 v = new Vector3(0, Consts.c*0.1, 0);
        Matrix m = doLorentzTransform(X, v);

        m.show();

        System.out.println("Time: " + m.getAt(0, 0)/Consts.c);
    }

}
