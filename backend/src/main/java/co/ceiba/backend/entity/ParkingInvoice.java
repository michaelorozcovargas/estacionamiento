package co.ceiba.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ParkingInvoice {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	private ParkingRegistry registry;

	@Column(nullable = false)
	private Integer price;

	public ParkingInvoice() {
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
	 * @return valor del campo registry
	 */
	public ParkingRegistry getRegistry() {
		return registry;
	}

	/**
	 * @param registry
	 *            nuevo valor para el campo registry
	 */
	public void setRegistry(ParkingRegistry registry) {
		this.registry = registry;
	}

	/**
	 * @return valor del campo price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            nuevo valor para el campo price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

}
