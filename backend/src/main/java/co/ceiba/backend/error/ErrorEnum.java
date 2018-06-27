package co.ceiba.backend.error;

/**
 * Enumeracion de los codigos de error
 * 
 * @author michael.orozco
 */
public enum ErrorEnum {

	UNAVAILABLE_SPACE(1, "No existe espacio disponible para el tipo de vehiculo");

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
	 * @param errorCode
	 *            nuevo valor para el campo errorCode
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return valor del campo errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            nuevo valor para el campo errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
