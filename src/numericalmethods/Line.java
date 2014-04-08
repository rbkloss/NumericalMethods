/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numericalmethods;

/**
 *
 * @author ricardo
 */
public class Line {

    private LinearFunction line;
    private float root;

    public Line(float[] p1, float[] p2) throws Exception {
        if (p1.length > 2 || p2.length > 2) {
            throw new Exception();
        } else {
            float m = (p2[1] - p1[1]) / (p2[0] - p1[0]);
            float c = p2[1] - m * p2[0];
            root = -c / m;
            line = new LinearFunction(new float[]{m, c});
        }
    }

    public LinearFunction getFunction() {
        return this.line;
    }

    public float getRoot() {
        return root;
    }
}
