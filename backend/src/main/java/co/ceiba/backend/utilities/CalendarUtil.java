package co.ceiba.backend.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Componente de utilidades para el manejo de fechas
 * 
 * @author michael.orozco
 */
@Component
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
			if (validDay.equals(dayOfWeek)) {
				validDate = true;
				break;
			}
		}

		return validDate;
	}

	public LocalDateTime formatDate(String stringFormat, Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(stringFormat);
		return LocalDateTime.parse(dateFormat.format(date));
	}

	public Date formatString(String stringFormat, String stringDate) {

		Date date;
		SimpleDateFormat dateFormat = new SimpleDateFormat(stringFormat);

		try {
			date = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			date = null;
		}

		return date;
	}

}
