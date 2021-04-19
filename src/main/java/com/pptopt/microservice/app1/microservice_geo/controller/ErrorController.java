package com.pptopt.microservice.app1.microservice_geo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.pptopt.microservice.app1.microservice_geo.exceptions.InternalServerException;
import com.pptopt.microservice.app1.microservice_geo.exceptions.NoItemFoundException;
import com.pptopt.microservice.app1.microservice_geo.exceptions.ValidationException;
import com.pptopt.microservice.app1.microservice_geo.pojo.CustomResponse;

/*
 * 
 * Controlador de errores
 * Mapea las excepciones en la aplicacion vs el estatus http que se desee  responder	
 * 
 * */

@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NoItemFoundException.class, FileNotFoundException.class })
	public ResponseEntity<CustomResponse> handleAllExceptions(Exception e) {
		CustomResponse er = customizeError(e, HttpStatus.NOT_FOUND);
		ResponseEntity<CustomResponse> respuesta = new ResponseEntity<>(er, er.getStatus());
		return respuesta;

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<CustomResponse> handle(ValidationException e) {
		CustomResponse er = customizeError(e, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(er, er.getStatus());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class, SQLException.class, NullPointerException.class, IOException.class,
			InternalServerException.class, IllegalStateException.class })
	public ResponseEntity<CustomResponse> handleAllExceptions(InternalServerException e) {
		CustomResponse er = customizeError(e, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(er, er.getStatus());
	}

	private CustomResponse customizeError(Exception e, HttpStatus httpStatus) {
		e.printStackTrace();
		CustomResponse error = new CustomResponse("" + httpStatus.value(), e.getMessage(), httpStatus);
		return error;
	}

}
