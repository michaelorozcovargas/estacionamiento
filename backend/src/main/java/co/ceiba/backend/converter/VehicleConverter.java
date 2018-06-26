package co.ceiba.backend.converter;

import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.model.VehicleModel;

/**
 * Utilidad para conversion de tipos de dato vehiculos
 * 
 * @author michael.orozco
 */
public final class VehicleConverter {

	/**
	 * Instancia unica de la clase
	 */
	private static final VehicleConverter INSTANCE = new VehicleConverter();

	/**
	 * Metodo constructor de la clase
	 */
	private VehicleConverter() {
		// Metodo vacio a proposito
	}

	/**
	 * @return unica instancia de la clase
	 */
	public static VehicleConverter getInstance() {
		return INSTANCE;
	}

	/**
	 * Permite convertir un objeto de tipo {@link VehicleModel} a uno de tipo
	 * {@link Vehicle}
	 * 
	 * @param vehicleModel
	 *            objeto a convertir
	 * @return objeto de tipo {@link Vehicle}
	 */
	public Vehicle getVehicle(VehicleModel vehicleModel) {

		Vehicle vehicle = new Vehicle();
		vehicle.setId(vehicle.getId());
		vehicle.setPlate(vehicleModel.getPlate());
		vehicle.setCubicCentimeters(vehicleModel.getCubicCentimeters());

		return vehicle;
	}

}
