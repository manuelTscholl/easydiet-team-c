/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.exception;

/**
 *
 * @author Stephan Svoboda
 */
public class NutrimentRuleException extends Exception {
    
    private String _errorMessage;

    public NutrimentRuleException(String errormessage) {

    }

    @Override
    public String getMessage() {
        return _errorMessage;
    }
}
