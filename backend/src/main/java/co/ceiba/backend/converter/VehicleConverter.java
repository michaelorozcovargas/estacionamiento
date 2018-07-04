package co.ceiba.backend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.model.VehicleModel;

/**
 * Utilidad para conversion de tipos de dato vehiculo
 * 
 * @author michael.orozco
 */
@Component
public class VehicleConverter implements Converter<Vehicle, VehicleModel> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.
	 * Object)
	 */
	@Override
	public VehicleModel convert(Vehicle source) {

		VehicleModel model = new VehicleModel();
		model.setPlate(source.getPlate());
		model.setCubicCentimeters(source.getCubicCentimeters());
		model.setVehicleType(source.getVehicleType().toString());

		return model;
	}

}
