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
	/**
	 * Formato para el manejo de fechas
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * Precios del estacionamiento
	 */
	public static final Integer PRICE_HOUR_CAR = 1000;
	public static final Integer PRICE_HOUR_MOTORCYCLE = 500;
	public static final Integer PRICE_DAY_CAR = 8000;
	public static final Integer PRICE_DAY_MOTORCYCLE = 4000;
	public static final Integer ADITIONAL_PRICE_BY_MOTORCYCLE_CC = 2000;

	/**
	 * Cilidraje que requiere costo adicional
	 */
	public static final Integer MAX_MOTORCYCLE_CC = 500;

	/**
	 * Hora minima para no contar como dia
	 */
	public static final Integer MIN_HOUR = 9;
	/**
	 * Hora maxima que cuenta como dia
	 */
	public static final Integer MAX_HOUR = 24;

}
