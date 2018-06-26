package co.ceiba.backend.builder;

import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.model.VehicleModel;

/**
 * Clase que define el patron Databuilder para objetos de tipo VehicleModel
 * 
 * @author michael.orozco
 */
public class VehicleModelBuilder {

	/**
	 * Placa del vehiculo
	 */
	private String plate;

	/**
	 * Centimetros cubicos
	 */
	private Integer cubicCentimeters;

	/**
	 * Tipo de vehiculo
	 */
	private String vehicleType;

	/**
	 * Metodo constructor de la clase
	 */
	public VehicleModelBuilder() {
		plate = "ABC123";
		cubicCentimeters = 200;
		vehicleType = VehicleTypeEnum.CAR.toString();
	}

	/**
	 * Asigna el valor de la placa
	 * 
	 * @param plate
	 *            placa
	 * @return VehicleModelBuilder
	 */
	public VehicleModelBuilder withPlate(String plate) {
		this.plate = plate;
		return this;
	}

	/**
	 * Asigna el valor de los centimetros cubicos
	 * 
	 * @param cubicCentimeters
	 *            centimetros cubicos
	 * @return VehicleModelBuilder
	 */
	public VehicleModelBuilder withCubicCentimeters(Integer cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
		return this;
	}

	/**
	 * Asigna el valor del tipo de vehiculo
	 * 
	 * @param vehicleType
	 *            tipo de vehiculo
	 * @return VehicleModelBuilder
	 */
	public VehicleModelBuilder withVehicleType(VehicleTypeEnum vehicleType) {
		this.vehicleType = vehicleType.toString();
		return this;
	}

	/**
	 * Construye un objeto con los datos definidos
	 * 
	 * @return objeto de tipo {@link VehicleModel}
	 */
	public VehicleModel build() {
		return new VehicleModel(plate, cubicCentimeters, vehicleType);
	}
}
