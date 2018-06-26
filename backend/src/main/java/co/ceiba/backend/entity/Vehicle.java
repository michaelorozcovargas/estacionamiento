package co.ceiba.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entidad que modela los datos de un vehiculo
 * 
 * @author michael.orozco
 */
@Entity
public class Vehicle {

	/**
	 * Identificador del vehiculo
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * Placa del vehiculo
	 */
	@Column
	private String plate;

	/**
	 * Centimetros cubicos
	 */
	@Column
	private Integer cubicCentimeters;

	/**
	 * Tipo de vehiculo
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private VehicleTypeEnum vehicleType;

	/**
	 * Metodo constructor de la clase
	 */
	public Vehicle() {
	}

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param id
	 *            Identificador del vehiculo
	 * @param plate
	 *            Placa del vehiculo
	 * @param cubicCentimeters
	 *            Centimetros cubicos
	 * @param vehicleType
	 *            Tipo de vehiculo
	 */
	public Vehicle(Integer id, String plate, Integer cubicCentimeters, VehicleTypeEnum vehicleType) {
		super();
		this.id = id;
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
	public VehicleTypeEnum getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType
	 *            nuevo valor para el campo vehicleType
	 */
	public void setVehicleType(VehicleTypeEnum vehicleType) {
		this.vehicleType = vehicleType;
	}

}
