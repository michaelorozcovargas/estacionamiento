package co.ceiba.backend.service;

import co.ceiba.backend.model.ParkingInvoiceModel;
import co.ceiba.backend.model.VehicleModel;

public interface ParkingDepartureService {

	public ParkingInvoiceModel departVehicle(VehicleModel vehicleModel);

}
