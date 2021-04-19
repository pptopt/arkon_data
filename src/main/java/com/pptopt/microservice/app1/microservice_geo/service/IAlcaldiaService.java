package com.pptopt.microservice.app1.microservice_geo.service;

import java.util.List;
import java.util.Optional;
import com.pptopt.microservice.app1.microservice_geo.entity.Alcaldia;

public interface IAlcaldiaService {
	
	public List<Alcaldia> findAll();
	
	public Optional<Alcaldia> findById(Integer id_alcaldia);
	

}
