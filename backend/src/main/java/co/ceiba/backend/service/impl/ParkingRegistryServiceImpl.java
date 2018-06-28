package co.ceiba.backend.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.ceiba.backend.constants.ApplicationConstants;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.error.ApplicationException;
import co.ceiba.backend.error.ErrorEnum;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.ParkingRegistryRepository;
import co.ceiba.backend.service.ParkingRegistryService;
import co.ceiba.backend.service.VehicleService;

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
	@Qualifier("vehicleService")
	private VehicleService vehicleService;

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
	 */
	public ParkingRegistryServiceImpl(ParkingRegistryRepository parkingRegistryRepository) {
		this.parkingRegistryRepository = parkingRegistryRepository;
	}

	/**
	 * Metodo encargado de generar los datos del registro
	 * 
	 * @param vehicle
	 *            vehiculo asociado
	 * 
	 * @return registro de estacionamiento
	 */
	private ParkingRegistry generateRegistry(Vehicle vehicle) {

		ParkingRegistry parkingRegistry = new ParkingRegistry();
		parkingRegistry.setEntryDate(Calendar.getInstance().getTime());
		parkingRegistry.setVehicle(vehicle);

		return parkingRegistry;
	}

	/**
	 * Permite consultar la existencia de espacio disponible para un tipo de
	 * vehiculo
	 * 
	 * @param VehicleModel
	 *            vehiculo
	 * 
	 * @return {@link Boolean} que indica la existencia de espacio disponible
	 */
	private synchronized boolean existsAvailableSpace(VehicleModel vehicleModel) {

		boolean existsAvailableSpace;

		VehicleTypeEnum vehicleType = VehicleTypeEnum.valueOf(vehicleModel.getVehicleType());

		Integer parkedVehiclesByType = parkingRegistryRepository.countParkedVehiclesByVehicleType(vehicleType);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.ceiba.backend.service.ParkingRegistryService#registerEntry(co.ceiba.
	 * backend.model.VehicleModel)
	 */
	@Override
	public boolean registerEntry(VehicleModel vehicleModel) throws ApplicationException {

		// Se verifica la disponibilidad para el tipo de vehiculo
		boolean availableSpace = existsAvailableSpace(vehicleModel);
		if (!availableSpace) {
			throw new ApplicationException(ErrorEnum.UNAVAILABLE_SPACE);
		}

		String plate = vehicleModel.getPlate();

		Vehicle vehicle = vehicleService.getVehicleByPlate(plate);

		if (vehicle == null) {
			vehicle = vehicleService.registerVehicle(vehicleModel);
		}

		ParkingRegistry parkingRegistry = generateRegistry(vehicle);

		return parkingRegistryRepository.save(parkingRegistry) != null;
	}

}
