package com.pptopt.microservice.app1.microservice_geo.controller;

/*
*
* CONTROLADOR DE LA
* ENTIDAD <<<<<ALCALDIA>>>>  
* NOMBRE EN LA BASE DE DATOS: BT_ALCALDIA
* 
* */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.pptopt.microservice.app1.microservice_geo.entity.Alcaldia;
import com.pptopt.microservice.app1.microservice_geo.exceptions.NoItemFoundException;
import com.pptopt.microservice.app1.microservice_geo.service.IAlcaldiaService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlcaldiaController {
	
	@Autowired
	private IAlcaldiaService service;
	
	/*
	 * 
	 * Lista de Todas las alcaldias
	 * */
	@GetMapping("/alcaldias/listAll")
	public List<String[]> findAll() throws Exception {
		
		List<Alcaldia> lst = service.findAll();
		
		if (lst.isEmpty()) {
			throw new NoItemFoundException("All");
		}
		else {
			return lst
			.stream()
			.map(alcaldia -> {
				String[] respuesta = {""+alcaldia.getId_alcaldia(),  alcaldia.getDes_alcaldia()}; 
				return respuesta;
			})
			.collect(Collectors.toList()); 
		}
	}
	
	
	/*
	 * Obtener detalle de alcaldia por Id
	 * 
	 * */
	@GetMapping("/alcaldias/getById/{id}")
	public Optional<Alcaldia> findById(@PathVariable Integer id) throws Exception {

		return Optional.ofNullable(service.findById(id)
				.orElseThrow(() -> new NoItemFoundException(""+id)));
	}
	
	
}
