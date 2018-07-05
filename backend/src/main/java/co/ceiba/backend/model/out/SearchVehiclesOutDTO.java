package co.ceiba.backend.model.out;

import java.util.ArrayList;
import java.util.List;

import co.ceiba.backend.model.ParkingRegistryModel;

/**
 * Encapsula la respuesta del servicio de consulta de vehiculos en el
 * estacionamiento
 * 
 * @author michael.orozco
 */
public class SearchVehiclesOutDTO extends ResponseDTO {

	/**
	 * Lista de registros
	 */
	private List<ParkingRegistryModel> registryList;

	/**
	 * @return valor del campo registryList
	 */
	public List<ParkingRegistryModel> getRegistryList() {
		if (registryList == null) {
			registryList = new ArrayList<>();
		}
		return registryList;
	}

	/**
	 * @param registryList
	 *            nuevo valor para el campo registryList
	 */
	public void setRegistryList(List<ParkingRegistryModel> registryList) {
		this.registryList = registryList;
	}

}
