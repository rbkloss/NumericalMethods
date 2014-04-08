/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numericalmethods;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricardo
 */
public class NumericalMethods {

    /**
     * Bissection method.
     *
     * @param function
     * @param a the inferior limit of the interval to search for a root
     * @param b the superior limit of the interval to search for a root
     * @return
     * @throws InvalidRangeException
     */
    public static float getFunctionRootBissection(LinearFunction function, float a, float b, int k) throws InvalidRangeException {
        float xMean;
        int i = 0;
        if (function.solve(a) * function.solve(b) < 0) {
            do {
                i++;
                xMean = (a + b) / 2;
                if (function.solve(a) * function.solve(xMean) < 0) {
                    b = xMean;
                } else if (function.solve(xMean) * function.solve(b) < 0) {
                    a = xMean;
                }

            } while (Math.abs(function.solve(xMean)) > 0.001 && i < k);
            System.out.println("Bissection Result is : " + function.solve(xMean) + " for x = " + xMean + " total iterations is : " + i);
            return xMean;
        } else {
            InvalidRangeException e = new InvalidRangeException();
            e.setMessage(a, b);
            throw e;
        }

    }

    /**
     * False position.
     *
     * @param f the function whose roots we want to find.
     * @param a
     * @param b
     * @param k The number of Max iterations.
     * @return
     * @throws InvalidRangeException
     */
    public static float getFunctionRootFP(LinearFunction f, float a, float b, int k) throws InvalidRangeException, Exception {
        float xMean;
        int i = 0;
        if (f.solve(a) * f.solve(b) < 0) {
            do {
                i++;
                Line l = new Line(new float[]{a, f.solve(a)}, new float[]{b, f.solve(b)});
//                System.out.println(l.getFunction().toString());
                xMean = l.getRoot();
                if (f.solve(xMean) * f.solve(b) < 0) {
                    a = xMean;
                } else {
                    b = xMean;
                }
//                System.out.println(" a : " + a + " b : " + b + " f(a)*f(b) = " + (f.solve(a) * f.solve(b)));

            } while (Math.abs(f.solve(xMean)) > 0.001 && i < k);
            System.out.println("False Position Result is : " + f.solve(xMean) + " for x = " + xMean + " total iterations is : " + i);
            return xMean;
        } else {
            InvalidRangeException e = new InvalidRangeException();
            e.setMessage(a, b);
            throw e;
        }
    }

    public static float getFunctionRootNewton(LinearFunction f, float x0, float tolerance, int k) {
        float x = x0;
        int i = 0;
        LinearFunction df = f.getDerivative();
        do {
//            System.out.println(" x" + i + " : " + x + " result : " + f.solve(x));
            i++;
            x = x - (f.solve(x) / df.solve(x));

        } while (Math.abs(f.solve(x)) > tolerance && i < k);
        System.out.println("Newton Result is : " + f.solve(x) + " for x = " + x + " total iterations is : " + i);
        return x;
    }

    public static float getFunctionRootSecant(LinearFunction f, float x0, float x1, float tolerance, int k) {
        int i = 0;
        float df;
        do {

//            System.out.println(" x" + (i + 1) + " : " + x1 + " result : " + f.solve(x1));
            i++;
            df = (f.solve(x1) - f.solve(x0)) / (x1 - x0);
            //update x0
            x0 = x1;
            //update x1
            x1 = x1 - (f.solve(x1) / df);


        } while (Math.abs(f.solve(x1)) > tolerance && i < k);
        System.out.println("Secant Result is : " + f.solve(x1) + " for x = " + x1 + " total iterations is : " + i + 1);
        return x1;
    }

    public static float[] findInterval(LinearFunction f) {
        float a = 100;
        float b = -100;
        float dt;
        do {
            dt = (float) (Math.random() * 100f);
            if (f.solve(dt) > 0 && (Math.abs(dt) < Math.abs(a))) {
                a = dt;
            } else if (f.solve(dt) > 0 && (Math.abs(dt) < Math.abs(a))) {
                b = dt;
            }
//            System.out.println(" a : "+a+" b : "+b+ "f(a)*f(b) = "+(f.solve(a)*f.solve(b)));
        } while (f.solve(a) * f.solve(b) > 0);
        if (a > b) {
            return new float[]{b, a};
        } else {
            return new float[]{a, b};
        }

    }
    
     public static float doLagrangeInterpolation(float x, ArrayList<Point2D.Float> points) {
        System.out.println("x is : " + x);
        float lagrangeFactor = 1;
        float answer = 0;
        for (int i = 0; i < points.size(); i++) {
            for (Point2D.Float current : points) {

                if (points.indexOf(current) != i) {
//                    System.out.println(" n : " + points.indexOf(current) + " i: " + i);
//                    System.out.printf("x is : %f, xn(%d) is : %f x%d is %f\n", x, points.indexOf(current), current.x, i, points.get(i).x);
                    lagrangeFactor = lagrangeFactor * (x - current.x) / (points.get(i).x - current.x);
//                    System.out.println("lagranfeFactors[" + i + "] is : " + lagrangeFactor);
                }
            }
            lagrangeFactor *= points.get(i).y;
//            System.out.println("lagranfeFactors[" + i + "] is : " + lagrangeFactor);
            answer = answer + lagrangeFactor;
            lagrangeFactor = 1;
        }
        return answer;
    }

    public static void main(String[] Args) {
//        final int k = 10000;
//
//        LinearFunction mFunction = new LinearFunction(new float[]{3, -9, 0, 1});
//        System.out.println(mFunction.toString() + mFunction.getDerivative().toString());
//        float[] range = NumericalMethods.findInterval(mFunction);
//        System.out.println("Interval found is : " + range[0] + " " + range[1]);
//        try {
//            NumericalMethods.getFunctionRootBissection(mFunction, range[0], range[1], 10000);
//        } catch (InvalidRangeException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            NumericalMethods.getFunctionRootFP(mFunction, range[0], range[1], 10000);
//        } catch (InvalidRangeException ex) {
//            Logger.getLogger(NumericalMethods.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(NumericalMethods.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        NumericalMethods.getFunctionRootNewton(mFunction, 0.5f, 0.0001f, k);
//
//        NumericalMethods.getFunctionRootSecant(mFunction, 0.5f, 0.3f, 0.0001f, k);

        ArrayList<Point2D.Float> points = new ArrayList<Point2D.Float>(5);
        points.add(new Point2D.Float(-1f, 0.54f));
        points.add(new Point2D.Float(0f, 1f));
        points.add(new Point2D.Float(1f, 0.54f));
        
        System.out.println("Interpolation result for x=3 : " + NumericalMethods.doLagrangeInterpolation(3, points));





    }
}
