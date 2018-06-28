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
	 * Metodo constructor de la clase
	 * 
	 * @param error
	 *            error asociado
	 */
	public ApplicationException(ErrorEnum error) {
		super(error.toString());
	}
}
