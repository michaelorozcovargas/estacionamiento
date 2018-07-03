package co.ceiba.backend.utilities;

import java.util.Calendar;

import org.springframework.stereotype.Component;

/**
 * Componente de utilidades para el manejo de fechas
 * 
 * @author michael.orozco
 */
@Component("calendarUtil")
public class CalendarUtil {

	/**
	 * @return fecha actual
	 */
	public Calendar getCurrentDate() {
		return Calendar.getInstance();
	}

	/**
	 * Valida si una fecha determinada coincide con alguno de los dias permitidos
	 * que son ingresados como parametro
	 * 
	 * @param date
	 *            fecha a validar
	 * @param validDays
	 *            dias validos
	 * 
	 * @return true en caso de que sea correcto, false en caso contrario
	 */
	public Boolean isValidDayOfWeek(Calendar date, Integer... validDays) {

		boolean validDate = false;

		Integer dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

		for (Integer validDay : validDays) {
			if (validDay == dayOfWeek) {
				validDate = true;
				break;
			}
		}

		return validDate;
	}
}
