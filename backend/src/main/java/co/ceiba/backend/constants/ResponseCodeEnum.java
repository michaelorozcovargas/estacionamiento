package co.ceiba.backend.constants;

/**
 * Enumeracion de los codigos de error
 * 
 * @author michael.orozco
 */
public enum ResponseCodeEnum {

	SUCCESSFULL("00", "Accion realizada con exito"),

	GENERAL_ERROR("01", "Se genero un error durante el proceso"),

	UNAVAILABLE_SPACE("01", "No existe espacio disponible para el tipo de vehiculo"),

	ACCESS_DENIED_BY_DATE_AND_PLATE("02",
			"Acceso no autorizado, las placas que inician por la letra A solo pueden ingresar los dias Domingo y Lunes");

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param responseCode
	 *            Codigo de error
	 * @param responseMessage
	 *            Mensaje de errror
	 */
	private ResponseCodeEnum(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	/**
	 * Codigo de error
	 */
	private String responseCode;

	/**
	 * Mensaje de errror
	 */
	private String responseMessage;

	/**
	 * @return valor del campo responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @return valor del campo responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

}
