package co.ceiba.backend.error;

/**
 * Enumeracion de los codigos de error
 * 
 * @author michael.orozco
 */
public enum ErrorEnum {

	UNAVAILABLE_SPACE("01", "No existe espacio disponible para el tipo de vehiculo"),

	ACCESS_DENIED_BY_DATE_AND_PLATE("02",
			"Acceso no autorizado, las placas que inician por la letra A solo pueden ingresar los dias Domingo y Lunes");

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param errorCode
	 *            Codigo de error
	 * @param errorMessage
	 *            Mensaje de errror
	 */
	private ErrorEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * Codigo de error
	 */
	private String errorCode;

	/**
	 * Mensaje de errror
	 */
	private String errorMessage;

	/**
	 * @return valor del campo errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return valor del campo errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
