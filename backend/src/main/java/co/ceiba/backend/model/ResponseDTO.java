package co.ceiba.backend.model;

/**
 * Almacena la respuesta de un servicio
 * 
 * @author michael.orozco
 */
public class ResponseDTO {

	/**
	 * Codigo de la respuesta
	 */
	private String code;
	/**
	 * Mensaje de la respuesta
	 */
	private String message;

	/**
	 * Metodo constructor de la clase
	 */
	public ResponseDTO() {
		// vacio a proposito
	}

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param code
	 *            codigo de la respuesta
	 * @param message
	 *            mensaje de la respuesta
	 */
	public ResponseDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * @return valor del campo code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            nuevo valor para el campo code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return valor del campo message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            nuevo valor para el campo message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
