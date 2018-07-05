package co.ceiba.backend.model;

import java.util.Date;

/**
 * Modelo que contiene la informacion de un registro de estacionamiento
 * 
 * @author michael.orozco
 */
public class ParkingRegistryModel {

	/**
	 * Vehiculo asociado
	 */
	private VehicleModel vehicle;

	/**
	 * Fecha de entrada
	 */
	private Date entryDate;

	/**
	 * Fecha de salida
	 */
	private Date outDate;

	/**
	 * @return valor del campo vehicleModel
	 */
	public VehicleModel getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicleModel
	 *            nuevo valor para el campo vehicleModel
	 */
	public void setVehicle(VehicleModel vehicleModel) {
		this.vehicle = vehicleModel;
	}

	/**
	 * @return valor del campo entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            nuevo valor para el campo entryDate
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return valor del campo outDate
	 */
	public Date getOutDate() {
		return outDate;
	}

	/**
	 * @param outDate
	 *            nuevo valor para el campo outDate
	 */
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

}
