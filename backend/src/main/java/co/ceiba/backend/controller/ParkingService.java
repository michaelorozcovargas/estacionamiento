package co.ceiba.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.backend.error.ApplicationException;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.service.ParkingRegistryService;

/**
 * Expone los servicios REST de estacionamiento
 * 
 * @author michael.orozco
 */
@RestController
@RequestMapping("/parkingService")
public class ParkingService {

	/**
	 * Servicios de estacionamiento
	 */
	@Autowired
	@Qualifier("parkingRegistryService")
	private ParkingRegistryService parkingRegistryService;

	/**
	 * Permite registrar la entrada de un vehiculo al estacionamiento
	 * 
	 * @param vehicleModel
	 * 
	 * @return {@link ResponseEntity} respuesta del servicio
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/registerEntry")
	@ResponseBody
	public ResponseEntity registerEntry(@RequestBody(required = true) VehicleModel vehicleModel) {
		ResponseEntity response = new ResponseEntity(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
		try {
			parkingRegistryService.registerEntry(vehicleModel);
		} catch (ApplicationException exception) {
			response = new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
