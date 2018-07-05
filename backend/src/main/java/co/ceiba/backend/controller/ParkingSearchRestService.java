package co.ceiba.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.backend.model.ParkingRegistryModel;
import co.ceiba.backend.model.out.ResponseDTO;
import co.ceiba.backend.model.out.SearchVehiclesOutDTO;
import co.ceiba.backend.service.ParkingSearchService;

/**
 * Expone los servicios REST de estacionamiento
 * 
 * @author michael.orozco
 */
@RestController
@RequestMapping("/parkingService")
public class ParkingSearchRestService extends Service {

	/**
	 * Servicios de estacionamiento
	 */
	@Autowired
	private ParkingSearchService parkingSearchService;

	/**
	 * Permite consultar los vehiculos que se encuentran en el estacionamiento
	 * 
	 * @return {@link ResponseEntity} respuesta del servicio
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/searchVehicles")
	@ResponseBody
	public ResponseEntity<ResponseDTO> searchVehicles() {

		ResponseEntity<ResponseDTO> response;
		SearchVehiclesOutDTO responseDTO = new SearchVehiclesOutDTO();

		try {

			List<ParkingRegistryModel> registryList = parkingSearchService.searchParkedVehicles();
			responseDTO.setRegistryList(registryList);

			response = buildResponse(responseDTO);

		} catch (Exception exception) {
			response = buildResponse(exception);
		}

		return response;
	}
}
