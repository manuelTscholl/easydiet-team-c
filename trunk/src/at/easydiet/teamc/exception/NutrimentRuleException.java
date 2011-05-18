package at.easydiet.teamc.exception;

/**
 * Exception thrown if a new role is added which contradicts a already available
 * nutrimentrule
 * 
 * @author Stephan Svoboda
 */
public class NutrimentRuleException extends Exception {

	private String _errorMessage;

	public NutrimentRuleException(String errormessage) {
		_errorMessage = errormessage;
	}

	@Override
	public String getMessage() {
		return _errorMessage;
	}
}
