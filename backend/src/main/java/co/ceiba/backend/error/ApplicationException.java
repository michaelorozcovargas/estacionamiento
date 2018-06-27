package co.ceiba.backend.error;

public class ApplicationException extends Exception {

	private ErrorEnum error;

	public ApplicationException(ErrorEnum error) {
		super();
		this.error = error;
	}

	/**
	 * @return valor del campo error
	 */
	public ErrorEnum getError() {
		return error;
	}

	/**
	 * @param error nuevo valor para el campo error
	 */
	public void setError(ErrorEnum error) {
		this.error = error;
	}

	
	
}
