package co.ceiba.backend.service;

import co.ceiba.backend.model.ParkingInvoiceModel;
import co.ceiba.backend.model.VehicleModel;

/**
 * Interface que define las funcionalidades de salida del estacionamiento
 * 
 * @author michael.orozco
 */
public interface ParkingDepartureService {

	/**
	 * Permite registrar la salida de un vehciculo del estacionamiento
	 * 
	 * @param vehicleModel
	 *            modelo del vehiculo
	 * 
	 * @return modelo que contiene la informacion de la factura generada
	 */
	public ParkingInvoiceModel departVehicle(VehicleModel vehicleModel);

}
