package co.ceiba.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.ceiba.backend.constants.ApplicationConstants;
import co.ceiba.backend.constants.StringConstants;
import co.ceiba.backend.constants.ResponseCodeEnum;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.error.ApplicationException;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.ParkingRegistryRepository;
import co.ceiba.backend.service.ParkingRegistryService;
import co.ceiba.backend.service.VehicleService;
import co.ceiba.backend.utilities.CalendarUtil;

/**
 * Clase que implementa los metodos definidos en la interface
 * {@link ParkingRegistryService}
 * 
 * @author michael.orozco
 */
@Service("parkingRegistryService")
public class ParkingRegistryServiceImpl implements ParkingRegistryService {

	/**
	 * Repositorio para la entidad {@link ParkingRegistry}
	 */
	@Autowired
	@Qualifier("parkingRegistryRepository")
	private ParkingRegistryRepository parkingRegistryRepository;

	/**
	 * Servicios de vehiculos
	 */
	@Autowired
	private VehicleService vehicleService;

	/**
	 * Utilidades para el manejo de fechas
	 */
	@Autowired
	@Qualifier("calendarUtil")
	private CalendarUtil calendarUtil;

	/**
	 * Metodo constructor de la clase
	 */
	public ParkingRegistryServiceImpl() {
		// Vacio a proposito
	}

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param parkingRegistryRepository
	 *            repositorio del objeto {@link ParkingRegistry}
	 * @param vehicleService
	 *            servicio de vehiculos
	 */
	public ParkingRegistryServiceImpl(ParkingRegistryRepository parkingRegistryRepository,
			VehicleService vehicleService) {
		this.parkingRegistryRepository = parkingRegistryRepository;
		this.vehicleService = vehicleService;
	}

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param parkingRegistryRepository
	 *            repositorio del objeto {@link ParkingRegistry}
	 * @param vehicleService
	 *            servicio de vehiculos
	 * 
	 * @param calendarUtil
	 *            utilidades para el manejo de fechas
	 */
	public ParkingRegistryServiceImpl(ParkingRegistryRepository parkingRegistryRepository,
			VehicleService vehicleService, CalendarUtil calendarUtil) {
		super();
		this.parkingRegistryRepository = parkingRegistryRepository;
		this.vehicleService = vehicleService;
		this.calendarUtil = calendarUtil;
	}

	/**
	 * Metodo encargado de generar los datos del registro
	 * 
	 * @param vehicle
	 *            vehiculo asociado
	 * 
	 * @return registro de estacionamiento
	 */
	public ParkingRegistry generateRegistry(Vehicle vehicle) {

		ParkingRegistry parkingRegistry = new ParkingRegistry();
		parkingRegistry.setEntryDate(calendarUtil.getCurrentDate().getTime());
		parkingRegistry.setVehicle(vehicle);

		return parkingRegistry;
	}

	/**
	 * Permite consultar la existencia de espacio disponible para un tipo de
	 * vehiculo
	 * 
	 * @param VehicleType
	 *            tipo de vehiculo
	 * 
	 * @return {@link Boolean} que indica la existencia de espacio disponible
	 */
	private synchronized boolean existsAvailableSpace(VehicleTypeEnum vehicleType) {

		boolean existsAvailableSpace;

		Integer parkedVehiclesByType = parkingRegistryRepository.countParkedVehiclesByType(vehicleType);

		switch (vehicleType) {

		case CAR:
			existsAvailableSpace = parkedVehiclesByType < ApplicationConstants.MAX_PARKED_CARS;
			break;

		case MOTORCYCLE:
			existsAvailableSpace = parkedVehiclesByType < ApplicationConstants.MAX_PARKED_MOTORCYCLE;
			break;

		default:
			existsAvailableSpace = false;
		}

		return existsAvailableSpace;
	}

	/**
	 * Permite validar si la placa puede ingresar teniendo en cuenta la fecha
	 * 
	 * @param plate
	 *            placa del vehiculo a ingresar
	 * 
	 * @return {@link Boolean} que indica si es posible ingresar o no
	 */
	private boolean isValidDayByPlate(String plate) {

		boolean isValidDay = true;

		if (plate.startsWith(StringConstants.A)) {
			isValidDay = calendarUtil.isValidDayOfWeek(calendarUtil.getCurrentDate(),
					ApplicationConstants.VALID_DAYS_FOR_PLATE_STARTS_WITH_A);
		}

		return isValidDay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.ceiba.backend.service.ParkingRegistryService#registerEntry(co.ceiba.
	 * backend.model.VehicleModel)
	 */
	@Override
	public boolean registerEntry(VehicleModel vehicleModel) throws ApplicationException {

		VehicleTypeEnum vehicleType = VehicleTypeEnum.valueOf(vehicleModel.getVehicleType());
		String vehiclePlate = vehicleModel.getPlate();

		// Se verifica la disponibilidad para el tipo de vehiculo
		boolean availableSpace = existsAvailableSpace(vehicleType);
		if (!availableSpace) {
			throw new ApplicationException(ResponseCodeEnum.UNAVAILABLE_SPACE);
		}

		// Se verifica si el vehiculo puede ingresar bajo el criterio de placa y fecha
		boolean isValidDay = isValidDayByPlate(vehiclePlate);
		if (!isValidDay) {
			throw new ApplicationException(ResponseCodeEnum.ACCESS_DENIED_BY_DATE_AND_PLATE);
		}

		// Se verifica si ya se encuentra registrado en el sistema
		Vehicle vehicle = vehicleService.getVehicleByPlate(vehiclePlate);

		// Se registra en caso de ser necesario
		if (vehicle == null) {
			vehicle = vehicleService.registerVehicle(vehicleModel);
		}

		ParkingRegistry parkingRegistry = generateRegistry(vehicle);

		return parkingRegistryRepository.save(parkingRegistry) != null;
	}

}
