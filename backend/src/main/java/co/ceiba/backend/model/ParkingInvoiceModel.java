package co.ceiba.backend.model;

/**
 * Modelo que contiene la informacion de una factura
 * 
 * @author michael.orozco
 */
public class ParkingInvoiceModel {

	/**
	 * Registro asociado a la factura
	 */
	private ParkingRegistryModel registry;

	/**
	 * Precio de la factura
	 */
	private Integer price;

	/**
	 * @return valor del campo registry
	 */
	public ParkingRegistryModel getRegistry() {
		return registry;
	}

	/**
	 * @param registry
	 *            nuevo valor para el campo registry
	 */
	public void setRegistry(ParkingRegistryModel registry) {
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
