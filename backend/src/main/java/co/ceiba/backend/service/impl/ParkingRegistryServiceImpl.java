package co.ceiba.backend.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.Vehicle;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.ceiba.backend.service.ParkingRegistryService#registerEntry(co.ceiba.
	 * backend.model.VehicleModel)
	 */
	@Override
	public boolean registerEntry(VehicleModel vehicleModel) {

		String plate = vehicleModel.getPlate();

		Vehicle vehicle = vehicleService.getVehicleByPlate(plate);

		if (vehicle == null) {
			vehicle = vehicleService.registerVehicle(vehicleModel);
		}

		ParkingRegistry parkingRegistry = generateRegistry(vehicle);

		return parkingRegistryRepository.save(parkingRegistry) != null;
	}

}
