package co.ceiba.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.ceiba.backend.converter.VehicleConverter;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.VehicleRepository;
import co.ceiba.backend.service.VehicleRegistryService;

/**
 * Implementacion de la interface {@link VehicleRegistryService}
 * 
 * @author michael.orozco
 */
public class VehicleRegistryServiceImpl implements VehicleRegistryService {

	/**
	 * Repositorio para la entidad {@link Vehicle}
	 */
	@Autowired
	@Qualifier("vehicleRepository")
	private VehicleRepository vehicleRepository;

	/**
	 * Metodo constructor de la clase
	 */
	public VehicleRegistryServiceImpl() {
	}

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param vehicleRepository
	 *            Repositorio para la entidad {@link Vehicle}
	 */
	public VehicleRegistryServiceImpl(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.ceiba.backend.service.VehicleRegistryService#registryVehicle(co.ceiba.
	 * backend.model.VehicleModel)
	 */
	@Override
	public boolean registryVehicle(VehicleModel vehicleModel) {
/*
		boolean registred = false;

		String plate = vehicleModel.getPlate();

		Vehicle vehicle = vehicleRepository.getVehicleByPlate(plate);

		if (vehicle == null) {

			vehicle = VehicleConverter.getInstance().getVehicle(vehicleModel);
			
			

		}

		return registred;
*/		
		return true;
	}

}
