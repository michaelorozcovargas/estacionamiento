package co.ceiba.backend.constants;

import java.util.Calendar;

/**
 * Contiene las constantes de la aplicacion
 * 
 * @author michael.orozco
 */
public final class ApplicationConstants {

	/**
	 * Metodo constructor de la clase
	 */
	private ApplicationConstants() {
		// Vacio a proposito
	}

	/**
	 * Cantidad maxima de carros
	 */
	public static final Integer MAX_PARKED_CARS = 20;
	/**
	 * Cantidad maxima de motos
	 */
	public static final Integer MAX_PARKED_MOTORCYCLE = 10;
	/**
	 * Dias validos para los vehiculos con placas que inician por la letra "A"
	 */
	public static final Integer[] VALID_DAYS_FOR_PLATE_STARTS_WITH_A = { Calendar.MONDAY, Calendar.SUNDAY };

}
