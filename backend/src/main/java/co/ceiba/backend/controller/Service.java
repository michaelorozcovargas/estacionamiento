package co.ceiba.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.ceiba.backend.constants.ResponseCodeEnum;
import co.ceiba.backend.constants.StringConstants;
import co.ceiba.backend.error.ApplicationException;
import co.ceiba.backend.model.ResponseDTO;

/**
 * Define el comportamiento general de los servicios
 * 
 * @author michael.orozco
 */
public abstract class Service {

	/**
	 * Metodo encargado de construir una respuesta en base a una excepcion generada
	 * 
	 * @param exception
	 *            excepcion generada
	 * 
	 * @return {@link ResponseEntity} respuesta que envuelve el error ocurrido
	 */
	protected ResponseEntity<ResponseDTO> buildResponse(Exception exception) {

		ResponseDTO responseDTO;

		if (exception instanceof ApplicationException) {
			ResponseCodeEnum responseCode = ((ApplicationException) exception).getError();
			responseDTO = new ResponseDTO(responseCode);

		} else {
			responseDTO = new ResponseDTO(ResponseCodeEnum.GENERAL_ERROR);
			responseDTO.setMessage(
					responseDTO.getMessage() + StringConstants.TWO_POINTS_WITH_SPACE + exception.getMessage());
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Metodo encargado de construir una respuesta exitosa
	 * 
	 * @param responseDTO
	 *            DTO que contiene la respuesta del servicio
	 * 
	 * @return {@link ResponseEntity} respuesta que envuelve la respuesta del
	 *         servicio
	 */
	protected ResponseEntity<ResponseDTO> buildResponse(ResponseDTO responseDTO) {
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

}
