package com.pptopt.microservice.app1.microservice_geo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pptopt.microservice.app1.microservice_geo.entity.PuntoDeRecorridoView;

@Repository
public interface PuntoDeRecorridoViewRepository extends JpaRepository<PuntoDeRecorridoView, Long> {

	/*
	 * Obtener lista de recorridos buscando por id de alcaldia
	 * */
	public List<PuntoDeRecorridoView> findByIdAlcaldia(Integer idAlcaldia);

	/*
	 * Obtener lista de puntos de recorrido buscando por id de vehiculo
	 * */	
	public List<PuntoDeRecorridoView> findByIdVehiculo(Integer idVehiculo);

	/*
	 * Obtener lista de unidades disponibles
	 * filtrar las que tengan actualizaciones en el
	 * mes anterior y el mes actual 
	 * */	
	@Query(value = ""+
			"select distinct id_vehiculo, vehicle_label "+ 
			"from vw_recorrido r "+
			"where " +
			"r.vehicle_current_status=?1 "+
			"and r.date_updated >= date_trunc('month',now()-interval '1' month)" 
			, nativeQuery = true)
	public List<Integer[]> findByVehicleCurrentStatus(Integer vehicleCurrentStatus);	
		
}
