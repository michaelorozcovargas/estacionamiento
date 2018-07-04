package co.ceiba.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.backend.converter.VehicleModelConverter;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.VehicleRepository;
import co.ceiba.backend.service.VehicleService;

/**
 * Implementacion de la interface {@link VehicleService}
 * 
 * @author michael.orozco
 */
@Service
public class VehicleServiceImpl implements VehicleService {

	/**
	 * Repositorio para la entidad {@link Vehicle}
	 */
	@Autowired
	private VehicleRepository vehicleRepository;

	/**
	 * Conversor para el tipo {@link VehicleModel}
	 */
	@Autowired
	private VehicleModelConverter vehicleModelConverter;

	/**
	 * Metodo constructor de la clase
	 */
	public VehicleServiceImpl() {
		// Vacio a proposito
	}

	/**
	 * 
	 * Metodo constructor de la clase
	 * 
	 * @param vehicleRepository
	 *            Repositorio para la entidad {@link Vehicle}
	 * @param vehicleModelConverter
	 *            conversor para el modelo de vehiculo
	 */
	public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleModelConverter vehicleModelConverter) {
		this.vehicleRepository = vehicleRepository;
		this.vehicleModelConverter = vehicleModelConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.ceiba.backend.service.VehicleService#getVehicleByPlate(java.lang.String)
	 */
	@Override
	public Vehicle getVehicleByPlate(String plate) {
		return vehicleRepository.findByPlate(plate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.ceiba.backend.service.VehicleService#registerVehicle(co.ceiba.backend.
	 * model.VehicleModel)
	 */
	@Override
	public Vehicle registerVehicle(VehicleModel vehicleModel) {
		return vehicleRepository.save(vehicleModelConverter.convert(vehicleModel));
	}
}
