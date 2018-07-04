package co.ceiba.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.backend.constants.ResponseCodeEnum;
import co.ceiba.backend.model.ResponseDTO;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.service.ParkingRegistryService;

/**
 * Expone los servicios REST de estacionamiento
 * 
 * @author michael.orozco
 */
@RestController
@RequestMapping("/parkingService")
public class ParkingService extends Service {

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
	public ResponseEntity<ResponseDTO> registerEntry(@RequestBody(required = true) VehicleModel vehicleModel) {

		ResponseEntity<ResponseDTO> response;
		ResponseDTO responseDTO;

		try {

			boolean registered = parkingRegistryService.registerEntry(vehicleModel);
			responseDTO = new ResponseDTO(registered ? ResponseCodeEnum.SUCCESSFULL : ResponseCodeEnum.GENERAL_ERROR);
			response = buildResponse(responseDTO);

		} catch (Exception exception) {
			response = buildResponse(exception);
		}

		return response;
	}

}
