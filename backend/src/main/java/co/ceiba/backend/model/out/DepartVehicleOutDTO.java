package co.ceiba.backend.model.out;

import co.ceiba.backend.model.ParkingInvoiceModel;

/**
 * Encapsula la informacion de la factura
 * 
 * @author michael.orozco
 */
public class DepartVehicleOutDTO extends ResponseDTO {

	/**
	 * Factura asociada
	 */
	private ParkingInvoiceModel parkingInvoiceModel;

	/**
	 * @return valor del campo parkingInvoiceModel
	 */
	public ParkingInvoiceModel getParkingInvoiceModel() {
		return parkingInvoiceModel;
	}

	/**
	 * @param parkingInvoiceModel
	 *            nuevo valor para el campo parkingInvoiceModel
	 */
	public void setParkingInvoiceModel(ParkingInvoiceModel parkingInvoiceModel) {
		this.parkingInvoiceModel = parkingInvoiceModel;
	}

}
