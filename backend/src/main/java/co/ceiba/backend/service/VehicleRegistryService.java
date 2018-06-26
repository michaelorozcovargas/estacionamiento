package co.ceiba.backend.service;

import co.ceiba.backend.model.VehicleModel;

/**
 * Define los metodos de registro de vehiculos
 * 
 * @author michael.orozco
 */
public interface VehicleRegistryService {

	/**
	 * Permite registrar la entrada de un vehiculo al estacionamiento
	 * 
	 * @param vehicleModel
	 *            modelo que contiene la informacion del vehiculo
	 * 
	 * @return {@link Boolean} true si la operacion fue exitosa, false en caso
	 *         contrario
	 */
	boolean registryVehicle(VehicleModel vehicleModel);

}
