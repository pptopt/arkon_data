package com.pptopt.microservice.app1.microservice_geo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pptopt.microservice.app1.microservice_geo.entity.PuntoDeRecorrido;
import com.pptopt.microservice.app1.microservice_geo.entity.PuntoDeRecorridoView;
import com.pptopt.microservice.app1.microservice_geo.repository.PuntoDeRecorridoRepository;
import com.pptopt.microservice.app1.microservice_geo.repository.PuntoDeRecorridoViewRepository;

@Service
public class PuntoDeRecorridoServiceImpl implements IPuntoDeRecorridoService {

	@Autowired
	private PuntoDeRecorridoViewRepository daoPuntoDeRecorridoView;
	@Autowired
	private PuntoDeRecorridoRepository daoPuntoDeRecorrido;
	
	@Override
	@Transactional(readOnly = true)
	public List<PuntoDeRecorridoView> findAll() {
		return (List<PuntoDeRecorridoView>) daoPuntoDeRecorridoView.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Optional<PuntoDeRecorridoView> findById(Long id_recorrido) {
		return Optional.ofNullable(daoPuntoDeRecorridoView.findById(id_recorrido).orElse(null));
	}
	
	@Override
	public List<PuntoDeRecorrido> findByIdAlcaldia(Integer idAlcaldia) {
		return (List<PuntoDeRecorrido>) daoPuntoDeRecorrido.findByIdAlcaldia(idAlcaldia);
	}	
	

	@Override
	@Transactional(readOnly = true)
	public List<PuntoDeRecorridoView> findByIdAlcaldiaView(Integer idAlcaldia) {
		return (List<PuntoDeRecorridoView>) daoPuntoDeRecorridoView.findByIdAlcaldia(idAlcaldia);
	}


	@Override
	public List<PuntoDeRecorridoView> findByIdVehiculo(Integer idVehiculo) throws Exception {
		return daoPuntoDeRecorridoView.findByIdVehiculo(idVehiculo);
	}

	@Override
	public List<Integer[]> findByVehicleCurrentStatus(Integer vehicleCurrentStatus) throws Exception {
		return daoPuntoDeRecorridoView.findByVehicleCurrentStatus(vehicleCurrentStatus);
	}
	
	@Override
	public Integer existeTimeStamp(Timestamp timestamp) {
		return daoPuntoDeRecorrido.existeTimeStamp(timestamp);
	}
	
	
	@Override
	public PuntoDeRecorrido update(PuntoDeRecorrido recorrido) {
		PuntoDeRecorrido result = null;
		try {
			result = daoPuntoDeRecorrido.save(recorrido);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	
}
