package co.ceiba.backend.error;

/**
 * Enumeracion de los codigos de error
 * 
 * @author michael.orozco
 */
public enum ErrorEnum {

	UNAVAILABLE_SPACE(1, "No existe espacio disponible para el tipo de vehiculo"),

	ACCESS_DENIED_BY_DATE_AND_PLATE(2,
			"Acceso no autorizado, las placas que inician por la letra A solo pueden ingresar los dias Domingo y Lunes");

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param errorCode
	 *            Codigo de error
	 * @param errorMessage
	 *            Mensaje de errror
	 */
	private ErrorEnum(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * Codigo de error
	 */
	private Integer errorCode;
	/**
	 * Mensaje de errror
	 */
	private String errorMessage;

	/**
	 * @return valor del campo errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * @return valor del campo errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return "{ \"code\" : " + errorCode + ", \"message\" : \"" + errorMessage + "\" }";
	}
}
