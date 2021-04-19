package com.pptopt.microservice.app1.microservice_geo.controller;

/*
 * CONTROLADOR DE LA
 * ENTIDAD <<<<<PUNTO DE RECORRIDO>>>>  
 * NOMBRE EN LA BASE DE DATOS: BT_RECORRIDO
 * 
 * */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import com.pptopt.microservice.app1.microservice_geo.entity.PuntoDeRecorridoView;
import com.pptopt.microservice.app1.microservice_geo.exceptions.NoItemFoundException;
import com.pptopt.microservice.app1.microservice_geo.pojo.CustomResponse;
import com.pptopt.microservice.app1.microservice_geo.pojo.UnidadVehicular;
import com.pptopt.microservice.app1.microservice_geo.service.IAlcaldiaService;
import com.pptopt.microservice.app1.microservice_geo.service.IPuntoDeRecorridoService;
import com.pptopt.microservice.app1.microservice_geo.service.Procesos;

@RestController
public class PuntoDeRecorridoController {
	
	@Autowired
	private IPuntoDeRecorridoService puntoDeRecorridoService;
	@Autowired
	private IAlcaldiaService alcaldiaService;
	
	private Logger log = LoggerFactory.getLogger(PuntoDeRecorridoController.class);
	
	@Value("${archivo.metrobus.rutaOrigen}")
	private String rutaOrigen;
	
	
	/**
	 * Obtener la lista completa de la Entidad Puntos de Recorrido 
	 * 
	 * */	
	@GetMapping("/puntosDeRecorrido/listAll")
	public List<PuntoDeRecorridoView> findAll() throws Exception {
		
		List<PuntoDeRecorridoView> lst = puntoDeRecorridoService.findAll();
		
		if (lst.isEmpty()) {
			throw new NoItemFoundException("All");
		}
		else {
			return lst
			.stream()
			.map(recorrido -> {
				return recorrido;
			})
			.collect(Collectors.toList());
		}
	}
	

		
	/**
	 * Obtener un solo Punto de Recorrido por id de recorrido
	 * 
	 * */
	@GetMapping("/puntosDeRecorrido/getById/{id}")
	public Optional<PuntoDeRecorridoView> findById(@Valid @PathVariable Long id) throws Exception {
		log.info("getPuntoDeRecorrido Id recibido: " + id);
		
		return Optional.ofNullable(puntoDeRecorridoService.findById(id)
				.orElseThrow(() -> new NoItemFoundException(""+id)));
		
	}
	
	
	/**
	 * Obtener lista de Puntos de Recorrido por id de vehiculo
	 * 
	 * */
	@GetMapping("/puntosDeRecorrido/getByIdVehiculo/{idVehiculo}")
	public List<PuntoDeRecorridoView> findByIdVehiculo(@Valid @PathVariable Integer idVehiculo) throws Exception {
		
		log.info("listPuntosDeRecorridoByIdVehiculo idVehiculo recibido: " + idVehiculo);				
		
		List<PuntoDeRecorridoView> lst = puntoDeRecorridoService.findByIdVehiculo(idVehiculo);

		if (lst.isEmpty()) {
			throw new NoItemFoundException("Puntos de Recorrido por id de vehiculo: " + idVehiculo);
		}
		else {
			return lst
			.stream()
			.map(recorrido -> {
				return recorrido;
			})
			.collect(Collectors.toList()); // convertir el map a List
		}

	}
	
	
	
	
	/**
	 * Obtener Lista de Puntos de Recorrido por id de alcaldia
	 * */
	@GetMapping("/puntosDeRecorrido/getByIdAlcaldia/{idAlcaldia}")
	public List<PuntoDeRecorridoView> findByIdAlcaldia(@Valid @PathVariable Integer idAlcaldia) throws Exception {
		
		log.info("listPuntosDeRecorridoByIdAlcaldia idAlcaldia recibido: " + idAlcaldia);				
		
		List<PuntoDeRecorridoView> lst = puntoDeRecorridoService.findByIdAlcaldiaView(idAlcaldia);

		if (lst.isEmpty()) {
			throw new NoItemFoundException("Puntos de Recorrido por id de Alcaldia: " + idAlcaldia);
		}
		else {
			return lst
			.stream()
			.map(recorrido -> {
				return recorrido;
			})
			.collect(Collectors.toList()); // convertir el map a List
		}

	}	
	
	
	/**
	 * Obtener Lista de Unidades disponibles
	 * <se filtra el vehicleCurrentStatus>
	 * */
	@GetMapping("/puntosDeRecorrido/getByVehicleCurrentStatus/{vehicleCurrentStatus}")
	public List<UnidadVehicular> findByVehicleCurrentStatus(@Valid @PathVariable Integer vehicleCurrentStatus) throws Exception {
		
		log.info("findByVehicleCurrentStatus Id recibido: " + vehicleCurrentStatus);				
		
		List<Integer[]> lst = puntoDeRecorridoService.findByVehicleCurrentStatus(vehicleCurrentStatus);

		if (lst.isEmpty()) {
			throw new NoItemFoundException("Puntos de Recorrido por vehicleCurrentStatus: " + vehicleCurrentStatus);
		}
		else {
			return lst
			.stream()
			.map(recorrido -> {
				return new UnidadVehicular(recorrido[0], recorrido[1]); 
			})
			.collect(Collectors.toList()); 
		}

	}		
	
	
	/*
	 * Correr este proceso cuando se requiera
	 * actualizar  
	 * */
	@GetMapping("/puntosDeRecorrido/updateProcess")
	public ResponseEntity<Object> updateRecorrido() throws Exception  {

		Procesos procesos = new Procesos(this.puntoDeRecorridoService, this.alcaldiaService);
		CustomResponse response = null;
		
		//Cargar el archivo desde la URL
		response = procesos.loadData(rutaOrigen);
		
		if(response.getStatus()==HttpStatus.OK) {

			//Actualizar el id de la alcaldia 
			response = procesos.actualizarIdAlcaldia();
			
		}

		return new ResponseEntity<>(response, response.getStatus());
	}
	
	
	
	
	
	
}
