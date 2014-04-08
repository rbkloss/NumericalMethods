/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numericalmethods;

import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
public class LinearFunction {

    ArrayList<Float> coefficients;

    /**
     *
     * @param coefficients the coefficients must be in the ascending order of
     * their degree. e.g. a+bx+cx².
     */
    LinearFunction(float[] coefficients) {
        this.coefficients = new ArrayList<Float>(10);
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients.add(Float.valueOf(coefficients[i]));
        }
    }

    /**
     *
     * @param x
     * @return
     */
    public float solve(float x) {
        float answer = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            answer = answer + coefficients.get(i) * (float) Math.pow(x, i);
        }
        return answer;
    }

    public LinearFunction getDerivative() {
        float[] derivativeCoefficients = new float[coefficients.size() - 1];
        for (int i = 0; i < coefficients.size() - 1; i++) {
            derivativeCoefficients[i] = coefficients.get(i + 1) * (i + 1);
        }
        return new LinearFunction(derivativeCoefficients);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("Line function : ");
        for (int i = 0; i < coefficients.size(); i++) {
            answer = answer.append(coefficients.get(i) + " * x^" + i + " + ");
        }
        answer.append("\n");
        return answer.toString();
    }
}
