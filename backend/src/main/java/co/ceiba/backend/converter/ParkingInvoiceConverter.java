package co.ceiba.backend.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.ceiba.backend.entity.ParkingInvoice;
import co.ceiba.backend.model.ParkingInvoiceModel;
import co.ceiba.backend.model.ParkingRegistryModel;

/**
 * Utilidad para conversion de tipos de dato de factura de estacionamiento
 * 
 * @author michael.orozco
 */
@Component
public class ParkingInvoiceConverter implements Converter<ParkingInvoice, ParkingInvoiceModel> {

	/**
	 * Conversor para el registro de estacionamiento
	 */
	@Autowired
	private ParkingRegistryConverter parkingRegistryConverter;

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param parkingRegistryConverter
	 */
	public ParkingInvoiceConverter(ParkingRegistryConverter parkingRegistryConverter) {
		super();
		this.parkingRegistryConverter = parkingRegistryConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.
	 * Object)
	 */
	@Override
	public ParkingInvoiceModel convert(ParkingInvoice source) {

		ParkingInvoiceModel model = new ParkingInvoiceModel();

		ParkingRegistryModel parkingRegistryModel = parkingRegistryConverter.convert(source.getRegistry());

		model.setRegistry(parkingRegistryModel);

		model.setPrice(source.getPrice());

		return model;
	}

}
