package co.ceiba.backend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.model.VehicleModel;

/**
 * Utilidad para conversion de tipos de dato vehiculos
 * 
 * @author michael.orozco
 */
@Component
public class VehicleModelConverter implements Converter<VehicleModel, Vehicle> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.
	 * Object)
	 */
	@Override
	public Vehicle convert(VehicleModel source) {
		Vehicle vehicle = new Vehicle();
		vehicle.setPlate(source.getPlate());
		vehicle.setCubicCentimeters(source.getCubicCentimeters());
		vehicle.setVehicleType(VehicleTypeEnum.valueOf(source.getVehicleType()));
		return vehicle;
	}

}
