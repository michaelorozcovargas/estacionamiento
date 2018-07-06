package co.ceiba.backend.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.model.ParkingRegistryModel;

/**
 * Utilidad para conversion de tipos de dato registro de estacionamiento
 * 
 * @author michael.orozco
 */
@Component
public class ParkingRegistryConverter implements Converter<ParkingRegistry, ParkingRegistryModel> {

	/**
	 * Conversor de vehiculos
	 */
	@Autowired
	private VehicleConverter vehicleConverter;

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param vehicleConverter
	 *            conversor para la clase {@link Vehicle}
	 */
	public ParkingRegistryConverter(VehicleConverter vehicleConverter) {
		super();
		this.vehicleConverter = vehicleConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.
	 * Object)
	 */
	@Override
	public ParkingRegistryModel convert(ParkingRegistry source) {

		ParkingRegistryModel model = new ParkingRegistryModel();

		if (source != null) {
			model.setEntryDate(source.getEntryDate());
			model.setOutDate(source.getOutDate());
			model.setVehicle(vehicleConverter.convert(source.getVehicle()));
		}

		return model;
	}

	/**
	 * Permite realizar la conversion de un listado de registros de estacionamiento
	 * 
	 * @param source
	 *            lista que contiene los registros
	 * 
	 * @return lista convertida
	 */
	public List<ParkingRegistryModel> convertList(List<ParkingRegistry> source) {

		List<ParkingRegistryModel> list = new ArrayList<>();

		for (ParkingRegistry parkingRegistry : source) {
			list.add(convert(parkingRegistry));
		}

		return list;
	}
}