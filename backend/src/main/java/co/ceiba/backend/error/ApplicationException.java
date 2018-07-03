package co.ceiba.backend.error;

/**
 * Excepcion personalizada para la aplicacion
 * 
 * @author michael.orozco
 */
public class ApplicationException extends Exception {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Error asociado a la excepcion
	 */
	private final ErrorEnum error;

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param error
	 *            error asociado
	 */
	public ApplicationException(ErrorEnum error) {
		this.error = error;
	}

	/**
	 * @return valor del campo error
	 */
	public ErrorEnum getError() {
		return error;
	}

}
