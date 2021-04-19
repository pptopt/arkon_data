package com.pptopt.microservice.app1.microservice_geo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import com.pptopt.microservice.app1.microservice_geo.entity.PuntoDeRecorrido;
import com.pptopt.microservice.app1.microservice_geo.entity.PuntoDeRecorridoView;

public interface IPuntoDeRecorridoService {
	
	public List<PuntoDeRecorridoView> findAll();
	
	public Optional<PuntoDeRecorridoView> findById(Long id_recorrido);
	
	public PuntoDeRecorrido update(PuntoDeRecorrido recorrido);
	
	public List<PuntoDeRecorrido> findByIdAlcaldia(Integer idAlcaldia);	

	public List<PuntoDeRecorridoView> findByIdAlcaldiaView(Integer idAlcaldia);

	public Integer existeTimeStamp(Timestamp timestamp);
	
	public List<PuntoDeRecorridoView> findByIdVehiculo(Integer idVehiculo) throws Exception;
	
	public List<Integer[]> findByVehicleCurrentStatus(Integer vehicleCurrentStatus) throws Exception;	

}
