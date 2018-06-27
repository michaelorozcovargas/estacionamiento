package co.ceiba.backend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidad que contiene el registro de vehiculos en el estacionamiento
 * 
 * @author michael.orozco
 */
@Entity
public class ParkingRegistry {

	/**
	 * Identificador del registro
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Fecha de entrada
	 */
	@Column(nullable = false)
	private Date entryDate;

	/**
	 * Fecha de salida
	 */
	@Column(nullable = true)
	private Date outDate;

	/**
	 * Vehiculo asociado
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Vehicle vehicle;

	/**
	 * Metodo constructor de la clase
	 */
	public ParkingRegistry() {
		// Vacio a proposito
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

	/**
	 * @return valor del campo vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle
	 *            nuevo valor para el campo vehicle
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
