package co.ceiba.backend.controller;

import org.springframework.http.ResponseEntity;

import co.ceiba.backend.error.ApplicationException;
import co.ceiba.backend.model.ResponseDTO;

public class Service {

	protected ResponseEntity<ResponseDTO> buildResponse(Exception exception) {

		ResponseDTO responseDTO = new ResponseDTO();

		if (exception instanceof ApplicationException) {

			ApplicationException applicationException = (ApplicationException) exception;

			responseDTO.setCode(applicationException.getError().getErrorCode());
			responseDTO.setMessage(applicationException.getError().getErrorMessage());
		}

	}

}
