package ift1025Tp1;


/**
 * Classe pour signaler problème de saisie (n'a pas été validée car incohérente, etc) 
 *
 */
public class ValidationException extends Exception {
	public ValidationException(String message) {
		super(message);
	}

	public ValidationException() {
		super();
	}
}
