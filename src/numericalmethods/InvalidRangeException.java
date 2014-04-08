/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package numericalmethods;

/**
 *
 * @author ricardo
 */
public class InvalidRangeException extends Exception {

    private String message;

    public void setMessage(float a, float b) {
        message = "range " + a + " " + b + " is invallid.";
    }
    public String getMessage(){
        return message;
    }
}
