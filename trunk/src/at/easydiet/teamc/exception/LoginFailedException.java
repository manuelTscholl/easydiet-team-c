/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.exception;

/**
 *
 * @author Stephan Svoboda
 */
public class LoginFailedException extends Exception{

    public LoginFailedException() {
    }

    public LoginFailedException(String message){
        super(message);
    }

}
