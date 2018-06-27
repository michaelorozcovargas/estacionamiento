package co.ceiba.backend.service;

import co.ceiba.backend.model.VehicleModel;

/**
 * Define los metodos de registro de entrada y salida de vehiculos
 * 
 * @author michael.orozco
 */
public interface ParkingRegistryService {

	/**
	 * Metodo que permite registrar el ingreso de un vehiculo en el estacionamiento
	 * 
	 * @param vehicle
	 *            vehiculo que ingresa
	 * 
	 * @return {@link Boolean} que define el exito o no de la operacion
	 */
	boolean registerEntry(VehicleModel vehicle);

}
