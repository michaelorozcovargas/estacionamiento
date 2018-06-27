package co.ceiba.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.ceiba.backend.converter.VehicleConverter;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.VehicleRepository;
import co.ceiba.backend.service.VehicleService;

/**
 * Implementacion de la interface {@link VehicleService}
 * 
 * @author michael.orozco
 */
@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

	/**
	 * Repositorio para la entidad {@link Vehicle}
	 */
	@Autowired
	@Qualifier("vehicleRepository")
	private VehicleRepository vehicleRepository;

	/**
	 * Metodo constructor de la clase
	 */
	public VehicleServiceImpl() {
	}

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param vehicleRepository
	 *            Repositorio para la entidad {@link Vehicle}
	 */
	public VehicleServiceImpl(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
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
		return vehicleRepository.save(VehicleConverter.getInstance().getVehicle(vehicleModel));
	}
}
