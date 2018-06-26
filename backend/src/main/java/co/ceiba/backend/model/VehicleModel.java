package co.ceiba.backend.model;

/**
 * Modelo que contiene la informacion de un vehiculo
 * 
 * @author michael.orozco
 */
public class VehicleModel {

	/**
	 * Identificador del vehiculo
	 */
	private Integer id;

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
	public VehicleModel() {
		super();
	}

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param plate
	 *            placa
	 * @param cubicCentimeters
	 *            centimetros cubicos
	 * @param vehicleType
	 *            tipo de vehiculo
	 */
	public VehicleModel(String plate, Integer cubicCentimeters, String vehicleType) {
		super();
		this.plate = plate;
		this.cubicCentimeters = cubicCentimeters;
		this.vehicleType = vehicleType;
	}

	/**
	 * @return valor del campo id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            nuevo valor para el campo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return valor del campo plate
	 */
	public String getPlate() {
		return plate;
	}

	/**
	 * @param plate
	 *            nuevo valor para el campo plate
	 */
	public void setPlate(String plate) {
		this.plate = plate;
	}

	/**
	 * @return valor del campo cubicCentimeters
	 */
	public Integer getCubicCentimeters() {
		return cubicCentimeters;
	}

	/**
	 * @param cubicCentimeters
	 *            nuevo valor para el campo cubicCentimeters
	 */
	public void setCubicCentimeters(Integer cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
	}

	/**
	 * @return valor del campo vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType
	 *            nuevo valor para el campo vehicleType
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

}
