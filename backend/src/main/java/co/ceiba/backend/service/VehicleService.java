package co.ceiba.backend.service;

import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.model.VehicleModel;

/**
 * Define las funcionalidades soportadas para vehiculos en el estacionamiento
 * 
 * @author michael.orozco
 */
public interface VehicleService {

	/**
	 * Permite buscar un vehiculo por su placa
	 * 
	 * @param plate
	 *            placa del vehiculo a buscar
	 * 
	 * @return vehiculo asociado a la placa
	 */
	Vehicle getVehicleByPlate(String plate);

	/**
	 * Permite registrar un vehiculo al estacionamiento
	 * 
	 * @param vehicleModel
	 *            modelo que contiene la informacion del vehiculo
	 * 
	 * @return vehiculo registrado
	 */
	Vehicle registerVehicle(VehicleModel vehicleModel);
}
