package co.ceiba.backend.service.impl;

import static co.ceiba.backend.constants.ApplicationConstants.ADITIONAL_PRICE_BY_MOTORCYCLE_CC;
import static co.ceiba.backend.constants.ApplicationConstants.DATE_FORMAT;
import static co.ceiba.backend.constants.ApplicationConstants.MAX_HOUR;
import static co.ceiba.backend.constants.ApplicationConstants.MAX_MOTORCYCLE_CC;
import static co.ceiba.backend.constants.ApplicationConstants.MIN_HOUR;
import static co.ceiba.backend.constants.ApplicationConstants.PRICE_DAY_CAR;
import static co.ceiba.backend.constants.ApplicationConstants.PRICE_DAY_MOTORCYCLE;
import static co.ceiba.backend.constants.ApplicationConstants.PRICE_HOUR_CAR;
import static co.ceiba.backend.constants.ApplicationConstants.PRICE_HOUR_MOTORCYCLE;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.backend.converter.ParkingInvoiceConverter;
import co.ceiba.backend.entity.ParkingInvoice;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.model.ParkingInvoiceModel;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.ParkingInvoiceRepository;
import co.ceiba.backend.repository.ParkingRegistryRepository;
import co.ceiba.backend.repository.VehicleRepository;
import co.ceiba.backend.service.ParkingDepartureService;
import co.ceiba.backend.utilities.CalendarUtil;

/**
 * Implementacion de las funcionalidades de salida de vehiculos definida en la
 * interface {@link ParkingDepartureService}
 * 
 * @author michael.orozco
 */
@Service
public class ParkingDepartureServiceImpl implements ParkingDepartureService {

	/**
	 * Repositorio para la entidad {@link ParkingRegistry}
	 */
	@Autowired
	private ParkingRegistryRepository parkingRegistryRepository;
	/**
	 * Repositorio para la entidad {@link Vehicle}
	 */
	@Autowired
	private VehicleRepository vehicleRepository;
	/**
	 * Utilidades de calendario
	 */
	@Autowired
	private CalendarUtil calendarUtil;
	/**
	 * Repositorio para la entidad {@link ParkingInvoice}
	 */
	@Autowired
	private ParkingInvoiceRepository parkingInvoiceRepository;
	/**
	 * Utilidad para la conversion de tipo de dato {@link ParkingInvoice}
	 */
	@Autowired
	private ParkingInvoiceConverter parkingInvoiceConverter;

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param parkingRegistryRepository
	 * @param vehicleRepository
	 * @param calendarUtil
	 * @param parkingInvoiceRepository
	 * @param parkingInvoiceConverter
	 */
	public ParkingDepartureServiceImpl(ParkingRegistryRepository parkingRegistryRepository,
			VehicleRepository vehicleRepository, CalendarUtil calendarUtil,
			ParkingInvoiceRepository parkingInvoiceRepository, ParkingInvoiceConverter parkingInvoiceConverter) {
		super();
		this.parkingRegistryRepository = parkingRegistryRepository;
		this.vehicleRepository = vehicleRepository;
		this.calendarUtil = calendarUtil;
		this.parkingInvoiceRepository = parkingInvoiceRepository;
		this.parkingInvoiceConverter = parkingInvoiceConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.ceiba.backend.service.ParkingDepartureService#departVehicle(co.ceiba.
	 * backend.model.VehicleModel)
	 */
	public ParkingInvoiceModel departVehicle(VehicleModel vehicleModel) {

		Vehicle vehicle = vehicleRepository.findByPlate(vehicleModel.getPlate());

		ParkingRegistry parkingRegistry = parkingRegistryRepository.findActiveRegistryByVehicle(vehicle);
		parkingRegistry.setOutDate(calendarUtil.getCurrentDate().getTime());

		ParkingInvoice parkingInvoice = generateParkingInvoice(parkingRegistry);

		parkingRegistryRepository.save(parkingRegistry);
		parkingInvoiceRepository.save(parkingInvoice);

		return parkingInvoiceConverter.convert(parkingInvoice);
	}

	/**
	 * Permite generar la factura asociada a la salida de un vehiculo del
	 * estacionamiento
	 * 
	 * @param parkingRegistry
	 *            registro del estacionamiento
	 * 
	 * @return factura generada
	 */
	public ParkingInvoice generateParkingInvoice(ParkingRegistry parkingRegistry) {

		ParkingInvoice parkingInvoice = new ParkingInvoice();

		parkingInvoice.setRegistry(parkingRegistry);

		parkingInvoice.setPrice(calculateParkingPrice(parkingRegistry));

		return parkingInvoice;
	}

	/**
	 * Calcula el costo a pagar para la salida del vehiculo
	 * 
	 * @param parkingRegistry
	 *            registro asociado
	 * 
	 * @return costo
	 */
	private Integer calculateParkingPrice(ParkingRegistry parkingRegistry) {

		LocalDateTime entryDate = calendarUtil.formatDate(DATE_FORMAT, parkingRegistry.getEntryDate());
		LocalDateTime outDate = calendarUtil.formatDate(DATE_FORMAT, parkingRegistry.getOutDate());

		Duration duration = Duration.between(entryDate, outDate);

		int hours = (int) (duration.toHours());
		int minutes = (int) (duration.toMinutes() % 60);

		hours = minutes > 0 ? hours + 1 : hours;

		int days = 0;
		days = determinateDays(hours, days);

		hours = determinateHours(hours);

		return calculateParkingPrice(parkingRegistry.getVehicle(), days, hours);
	}

	/**
	 * Determina la cantidad de horas a cobrar en base a las reglas de negocio
	 * establecidas
	 * 
	 * @param hours
	 *            cantidad de horas en que el vehiculo permanecio en el
	 *            estacionamiento
	 * 
	 * @return cantidad de horas a cobrar
	 */
	private int determinateHours(int hours) {

		if (hours < MIN_HOUR) {
			return hours;

		} else {
			if (hours < MAX_HOUR) {
				return 0;

			} else {
				hours -= MAX_HOUR;
				return determinateHours(hours);
			}
		}
	}

	/**
	 * Determina la cantidad de dias a cobrar en base a las reglas de negocio
	 * establecidas
	 * 
	 * @param hours
	 *            cantidad de horas en que el vehiculo permanecio en el
	 *            estacionamiento
	 * @param days
	 *            contabilizado de dias utilizado por el metodo recursivo
	 * 
	 * @return cantidad de dias a cobrar
	 */
	private int determinateDays(int hours, int days) {

		if (hours < MIN_HOUR) {
			return days;

		} else {

			if (hours < MAX_HOUR) {
				days += 1;
				return days;

			} else {
				days++;
				hours -= MAX_HOUR;
				return determinateDays(hours, days);
			}
		}
	}

	/**
	 * Permite calcular el costo del estacionamiento para un vehiculo en base a su
	 * tipo y cantidad de tiempo en que permanecio
	 * 
	 * @param vehicle
	 *            vehiculo asociado
	 * @param days
	 *            dias a cobrar
	 * @param hours
	 *            horas a cobrar
	 * 
	 * @return costo del estacionamiento para el vehiculo
	 */
	private Integer calculateParkingPrice(Vehicle vehicle, int days, int hours) {

		int price = 0;
		VehicleTypeEnum vehicleType = vehicle.getVehicleType();

		switch (vehicleType) {

		case CAR:
			price += days * PRICE_DAY_CAR;
			price += hours * PRICE_HOUR_CAR;
			break;

		case MOTORCYCLE:
			price += days * PRICE_DAY_MOTORCYCLE;
			price += hours * PRICE_HOUR_MOTORCYCLE;

			if (vehicle.getCubicCentimeters() > MAX_MOTORCYCLE_CC) {
				price += ADITIONAL_PRICE_BY_MOTORCYCLE_CC;
			}

			break;

		default:
			break;
		}

		return price;
	}

}
