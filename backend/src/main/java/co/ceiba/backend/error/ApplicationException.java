package co.ceiba.backend.error;

import co.ceiba.backend.constants.ResponseCodeEnum;

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
	private final ResponseCodeEnum error;

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param error
	 *            error asociado
	 */
	public ApplicationException(ResponseCodeEnum error) {
		this.error = error;
	}

	/**
	 * @return valor del campo error
	 */
	public ResponseCodeEnum getError() {
		return error;
	}

}
