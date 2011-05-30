/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.exception;

/**
 *
 * @author Stephan Svoboda
 */
public class NoDietPlanException extends Exception{

    public NoDietPlanException() {
    }

    public NoDietPlanException(String message){
        super(message);
    }

}
