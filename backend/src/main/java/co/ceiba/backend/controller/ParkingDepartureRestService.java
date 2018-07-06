package co.ceiba.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.backend.model.ParkingInvoiceModel;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.model.out.DepartVehicleOutDTO;
import co.ceiba.backend.model.out.ResponseDTO;
import co.ceiba.backend.service.ParkingDepartureService;

/**
 * Expone los servicios REST de estacionamiento
 * 
 * @author michael.orozco
 */
@RestController
@RequestMapping("/parkingService")
public class ParkingDepartureRestService extends Service {

	/**
	 * Servicios de estacionamiento
	 */
	@Autowired
	private ParkingDepartureService parkingDepartureService;

	/**
	 * Permite registrar la salida de un vehiculo del estacionamiento
	 * 
	 * @param vehicleModel
	 * 
	 * @return {@link ResponseEntity} respuesta del servicio
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path = "/departVehicle")
	@ResponseBody
	public ResponseEntity<ResponseDTO> departVehicle(@RequestBody(required = true) VehicleModel vehicleModel) {

		ResponseEntity<ResponseDTO> response;
		DepartVehicleOutDTO responseDTO = new DepartVehicleOutDTO();

		try {

			ParkingInvoiceModel invoiceModel = parkingDepartureService.departVehicle(vehicleModel);
			responseDTO.setParkingInvoiceModel(invoiceModel);
			response = buildResponse(responseDTO);

		} catch (Exception exception) {
			response = buildResponse(exception);
		}

		return response;
	}
}
