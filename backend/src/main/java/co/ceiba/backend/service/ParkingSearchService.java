package co.ceiba.backend.service;

import java.util.List;

import co.ceiba.backend.model.ParkingRegistryModel;

/**
 * Define las funcionalidades de busqueda en el estacionamiento
 * 
 * @author michael.orozco
 */
public interface ParkingSearchService {

	/**
	 * Permite consultar los vehiculos que se encuentran en el estacionamiento
	 * 
	 * @return {@link List} lista con los vehiculos
	 */
	List<ParkingRegistryModel> searchParkedVehicles();

}
