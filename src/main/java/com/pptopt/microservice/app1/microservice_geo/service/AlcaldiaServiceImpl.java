package com.pptopt.microservice.app1.microservice_geo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pptopt.microservice.app1.microservice_geo.entity.Alcaldia;
import com.pptopt.microservice.app1.microservice_geo.repository.AlcaldiaRepository;

@Service
public class AlcaldiaServiceImpl implements IAlcaldiaService {
	
	@Autowired
	private AlcaldiaRepository dao; 

	@Override
	@Transactional(readOnly = true) 
	public List<Alcaldia> findAll() {
		return (List<Alcaldia>) dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alcaldia> findById(Integer id_alcaldia) {
		return Optional.ofNullable(dao.findById(id_alcaldia).orElse(null));
	}
	


}
