package co.ceiba.backend.model.out;

import co.ceiba.backend.constants.ResponseCodeEnum;

/**
 * Almacena la respuesta de un servicio
 * 
 * @author michael.orozco
 */
public class ResponseDTO {

	/**
	 * Codigo de la respuesta
	 */
	private String responseCode;
	/**
	 * Mensaje de la respuesta
	 */
	private String responseMessage;

	/**
	 * Metodo constructor de la clase
	 */
	public ResponseDTO() {
		this(ResponseCodeEnum.SUCCESSFULL);
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
		this.responseCode = code;
		this.responseMessage = message;
	}

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param responseCodeEnum
	 *            codigo de respuesta asociado
	 */
	public ResponseDTO(ResponseCodeEnum responseCodeEnum) {
		this(responseCodeEnum.getResponseCode(), responseCodeEnum.getResponseMessage());
	}

	/**
	 * @return valor del campo code
	 */
	public String getCode() {
		return responseCode;
	}

	/**
	 * @param code
	 *            nuevo valor para el campo code
	 */
	public void setCode(String code) {
		this.responseCode = code;
	}

	/**
	 * @return valor del campo message
	 */
	public String getMessage() {
		return responseMessage;
	}

	/**
	 * @param message
	 *            nuevo valor para el campo message
	 */
	public void setMessage(String message) {
		this.responseMessage = message;
	}

}
